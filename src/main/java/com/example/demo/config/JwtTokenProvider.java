package com.example.demo.config;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private final String secret = "secret-key";
    private final long validityInMs = 3600000;

    public String createToken(String username, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
