package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount register(UserAccount user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserAccount login(String username, String password) {
        UserAccount user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("not found");
        }
        return user;
    }
}
