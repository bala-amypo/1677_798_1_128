package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String register(String username, String email, String password, String role) {
        if (userRepository.existsByUsername(username)) {
            return "Username already exists";
        }
        
        if (userRepository.existsByEmail(email)) {
            return "Email already registered";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role != null ? role : "INVESTOR");

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            return "Invalid username or password";
        }
        
        if (!password.equals(user.getPassword())) {
            return "Invalid username or password";
        }
        
        return "Login successful! Welcome " + user.getUsername();
    }
}