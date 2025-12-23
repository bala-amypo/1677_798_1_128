package com.example.demo.Impl;

import com.example.demo.entity.User;
import com.example.demo.entity.enums.RoleType;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User registerUser(String username, String password, String email, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(RoleType.valueOf(role.toUpperCase()));
        return userRepository.save(user);
    }
    
    @Override
    public String authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return "JWT_TOKEN_GENERATED";
                    }
                    return null;
                })
                .orElse(null);
    }
    
    @Override
    public User getCurrentUser() {
        // Implementation depends on security context
        return null;
    }
    
    @Override
    public void logoutUser() {
        // Implementation depends on token management
    }
}