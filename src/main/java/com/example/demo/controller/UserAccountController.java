package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Accounts", description = "User account management endpoints")
public class UserAccountController {
    
    private final UserAccountService userAccountService;
    
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
    
    @GetMapping("/")
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        List<UserAccount> users = userAccountService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserAccount> getUserById(@PathVariable Long id) {
        UserAccount user = userAccountService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update user status")
    public ResponseEntity<UserAccount> updateUserStatus(
            @PathVariable Long id, 
            @RequestBody Map<String, Boolean> request) {
        boolean active = request.get("active");
        UserAccount updated = userAccountService.updateUserStatus(id, active);
        return ResponseEntity.ok(updated);
    }
}