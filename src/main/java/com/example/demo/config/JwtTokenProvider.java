package com.example.demo.config;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String username) {
        // Minimal stub for application startup
        return "dummy-jwt-token-for-" + username;
    }

    public String getUsernameFromToken(String token) {
        // Minimal stub logic
        if (token == null) {
            return null;
        }
        return token.replace("dummy-jwt-token-for-", "");
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy-jwt-token-for-");
    }
}
