package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = authService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, 
                                                     @RequestParam String password) {
        Map<String, String> response = authService.login(username, password);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token.replace("Bearer ", ""));
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        boolean isValid = authService.validateToken(token.replace("Bearer ", ""));
        return ResponseEntity.ok(isValid);
    }
    
    @GetMapping("/user")
    public ResponseEntity<User> getUserFromToken(@RequestHeader("Authorization") String token) {
        User user = authService.getUserFromToken(token.replace("Bearer ", ""));
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = authService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = authService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}