package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl
        implements UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountServiceImpl(
            UserAccountRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public UserAccount register(UserAccount user) {
        return repository.save(user);
    }

    @Override
    public UserAccount findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found: " + username
                        ));
    }
}
