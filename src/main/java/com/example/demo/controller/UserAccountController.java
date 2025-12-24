package com.example.demo.controller;

import com.example.demo.entity.UserAccount;
import com.example.demo.config.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserAccountController {

    private final UserAccountService service;
    private final JwtUtil jwtUtil;

    public UserAccountController(UserAccountService service) {
        this.service = service;
        this.jwtUtil = new JwtUtil("secret", 3600000);
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {
        service.login(username, password);
        return jwtUtil.generateToken(username);
    }
}
