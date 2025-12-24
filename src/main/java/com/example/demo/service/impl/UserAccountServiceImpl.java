package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.entity.enums.RoleType;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
    
    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public UserAccountServiceImpl(UserAccountRepository repository, 
                                 PasswordEncoder passwordEncoder,
                                 JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    public UserAccount registerUser(UserAccount user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        if (repository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Default role to INVESTOR if not specified
        if (user.getRole() == null) {
            user.setRole(RoleType.INVESTOR);
        }
        
        return repository.save(user);
    }
    
    @Override
    public String authenticate(String username, String password) {
        UserAccount user = repository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        
        if (!user.getActive()) {
            throw new IllegalArgumentException("User account is deactivated");
        }
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        
        return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
    
    @Override
    public UserAccount getUserById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
    
    @Override
    public List<UserAccount> getAllUsers() {
        return repository.findAll();
    }
    
    @Override
    public UserAccount updateUserStatus(Long id, boolean active) {
        UserAccount user = getUserById(id);
        user.setActive(active);
        return repository.save(user);
    }
}