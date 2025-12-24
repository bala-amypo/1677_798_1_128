package com.example.demo.config;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JwtUtil {

    /**
     * Generates a simple pseudo-token.
     * This is NOT a real JWT.
     * It exists only to unblock compilation and controller logic.
     */
    public String generateToken(String username) {
        String rawToken = username + ":token";
        return Base64.getEncoder()
                .encodeToString(rawToken.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Optional helper if you ever want to decode it
     */
    public String extractUsername(String token) {
        byte[] decoded = Base64.getDecoder().decode(token);
        String decodedToken = new String(decoded, StandardCharsets.UTF_8);
        return decodedToken.split(":")[0];
    }
}
