package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repo;

    public AuthServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserAccount register(UserAccount user) {
        return repo.save(user);
    }

    @Override
    public String login(String username, String password) {
        UserAccount user = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }
        return "LOGIN_SUCCESS";
    }
}
