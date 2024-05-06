package com.example.pottery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class GoogleAuthService {
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    public boolean verifyToken(String token) {
        try {
            String url = "https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=" + token;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set request method
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", token);
            con.setRequestProperty("Accept", "application/json");
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 200
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Check if response contains error
                return !response.toString().contains("error");
            } else {
                // Handle non-200 response codes
                System.out.println("Failed to verify access token. Response code: " + responseCode);
                return false;
            }
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
            return false;
        }
    }
}
