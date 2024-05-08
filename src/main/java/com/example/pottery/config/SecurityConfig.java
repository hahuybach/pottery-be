package com.example.pottery.config;

import com.example.pottery.db2.serviceImpl.HistoryServiceImpl;
import com.example.pottery.filter.IPAddressFilter;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    @Autowired
    private HistoryServiceImpl historyServiceImpl;
//    @Autowired
//    private GoogleTokenFilter googleTokenFilter;
    @Bean
    public Filter ipAddressFilter() {
        return new IPAddressFilter(historyServiceImpl);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Value("${frontend.url}")
    private String frontendUrl;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:3000")); // Replace "*" with the appropriate allowed origins
                    corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Specify the allowed HTTP methods
                    corsConfig.setAllowedHeaders(List.of("Content-Type", "Accept", "X-Requested-With", "remember-me", "Authorization")); // Specify the allowed headers
                    corsConfig.setAllowCredentials(true); // Set to true if you need to allow credentials
                    corsConfig.setMaxAge(3600L); // Set the maximum age of the CORS pre-flight response
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", corsConfig); // Apply the CORS configuration to all paths
                    return corsConfig;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//                    auth.requestMatchers(HttpMethod.GET, "/**").permitAll();
//                    auth.requestMatchers(HttpMethod.POST, "/**").permitAll();
//                    auth.requestMatchers(HttpMethod.PUT, "/**").permitAll();
//                    auth.requestMatchers("/api/authenticated").permitAll();
//                    auth.requestMatchers("/login").permitAll();
//                    auth.requestMatchers("/favicon.ico").permitAll();
                    auth.anyRequest().permitAll();
                })
//                .addFilterBefore(googleTokenFilter,BasicAuthenticationFilter.class)
                .addFilterBefore(ipAddressFilter(),BasicAuthenticationFilter.class)
                .build();
    }

    public class JwtTokenFilter extends BasicAuthenticationFilter {

        public JwtTokenFilter(AuthenticationManager authenticationManager) {
            super(authenticationManager);
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
            String idTokenString = request.getHeader("Authorization");

            if (idTokenString != null && idTokenString.startsWith("Bearer ")) {
                String token = idTokenString.substring(7);
                System.out.println(token);
                try {
                    HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
                    GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
                    GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                            .setAudience(Collections.singletonList("762978610620-sl0854e4l0u72geruu4mp4go0jmqsij7.apps.googleusercontent.com"))
                            .build();
//                    Lỗi trả về null???
//                    GoogleIdToken idToken = verifier.verify(token);
//                    if (idToken != null) {
//                        Payload payload = idToken.getPayload();
//
//                        // Print user identifier
//                        String userId = payload.getSubject();
//                        System.out.println("User ID: " + userId);
//
//                        // Get profile information from payload
//                        String email = payload.getEmail();
//                        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//                        String name = (String) payload.get("name");
//                        String pictureUrl = (String) payload.get("picture");
//                        String locale = (String) payload.get("locale");
//                        String familyName = (String) payload.get("family_name");
//                        String givenName = (String) payload.get("given_name");
//
//                        // Use or store profile information
//                        // ...
//
//                    } else {
//                        System.out.println("Invalid ID token.");
//                    }
                } catch (GeneralSecurityException e) {
                    // Exception during token verification
                    SecurityContextHolder.clearContext();
                }
            }

            chain.doFilter(request, response);
        }
    }
}

