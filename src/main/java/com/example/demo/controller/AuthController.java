package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.UserAccount;
import com.example.demo.config.JwtTokenProvider;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserAccountRepository userRepo;

    public AuthController(AuthenticationManager authManager, JwtTokenProvider tokenProvider, UserAccountRepository userRepo) {
        this.authenticationManager = authManager;
        this.tokenProvider = tokenProvider;
        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        UserAccount user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        String token = tokenProvider.generateToken(auth, user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}