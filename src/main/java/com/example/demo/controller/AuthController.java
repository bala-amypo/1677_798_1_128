package com.example.demo.controller;

import com.example.demo.Impl.AuthService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestParam String username,
                                            @RequestParam String password,
                                            @RequestParam String email,
                                            @RequestParam String role) {
        User user = authService.registerUser(username, password, email, role);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username,
                                           @RequestParam String password) {
        String token = authService.authenticateUser(username, password);
        if (token != null) {
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser() {
        authService.logoutUser();
        return ResponseEntity.ok().build();
    }
}