package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private final String secret = "your-256-bit-secret-your-256-bit-secret-your-256-bit-secret";
    private final long validityInMs = 3600000; // 1h

    public JwtUtil getJwtUtil() {
        return new JwtUtil(secret, validityInMs);
    }
}