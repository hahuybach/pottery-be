package com.example.pottery;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Hello, home";
    }
    @GetMapping("/secured")
    public String secured (){
        return "Hello, home";
    }
    @GetMapping("/test-login")
    public String testLogin (){
        return "Test login";
    }
    @GetMapping("/user/me")
    public Map<String, Object> userDetails(@AuthenticationPrincipal OAuth2User user) {
        if (user != null) {
            // Người dùng đã đăng nhập
            return user.getAttributes();
        } else {
            // Người dùng chưa đăng nhập
            return Collections.emptyMap(); // Hoặc trả về thông báo khác tùy thuộc vào yêu cầu của bạn
        }
    }
    @GetMapping("/api/authenticated")
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }
}
