package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}