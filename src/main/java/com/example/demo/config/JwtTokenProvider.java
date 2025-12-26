package com.example.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtTokenProvider {
    private final SecretKey key;
    private final long validity;

    public JwtTokenProvider(String secret, long validity) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.validity = validity;
    }
    
    // ... generateToken, validateToken, etc.
}