package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    /**
     * Defines the JwtUtil bean.
     * Use a plain alphanumeric string to avoid Base64 decoding errors (like illegal characters).
     */
    @Bean
    public JwtUtil jwtUtil() {
        // Must be at least 32 characters for HS256
        String secret = "YourSuperSecretAlphanumericKeyForInvestorPortfolio2025"; 
        long validityInMs = 3600000; // 1 hour
        return new JwtUtil(secret, validityInMs);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/auth/**", "/status", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // Role-based protection for specific investor management
                .requestMatchers("/api/investors/**").hasAnyRole("ADMIN", "ANALYST")
                // All other /api/** endpoints require authentication
                .requestMatchers("/api/**").authenticated()
            )
            // Add our custom JWT filter before the standard authentication filter
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}