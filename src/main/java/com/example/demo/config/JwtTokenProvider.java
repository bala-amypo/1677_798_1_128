package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long validityInMs;

    // matches requirement: new JwtUtil(String secret, long validityInMs)
    public JwtTokenProvider() {
        this.secretKey = "change-me-secret-key";
        this.validityInMs = 3600_000L; // 1 hour
    }

    public String createToken(String username, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        List<String> authorities = roles.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", authorities);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsername(token);
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) getAllClaims(token).get("roles");
        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }

    public String getUsername(String token) {
        return getAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception ex) {
            return false;
        }
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
