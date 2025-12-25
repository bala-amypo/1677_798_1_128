package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Accounts")
public class UserAccountController {
    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserAccount get(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/")
    public List<UserAccount> list() {
        return service.getAllUsers();
    }
}