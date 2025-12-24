package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {
    
    private final UserAccountService userAccountService;
    
    public AuthController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        UserAccount registeredUser = userAccountService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    @Operation(summary = "Authenticate user and get JWT token")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        String token = userAccountService.authenticate(username, password);
        
        return ResponseEntity.ok(Map.of("token", token));
    }
}