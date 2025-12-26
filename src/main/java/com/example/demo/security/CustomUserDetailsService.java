package com.example.demo.security;

import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    public CustomUserDetailsService(
            UserAccountRepository userAccountRepository
    ) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails
    loadUserByUsername(String username) {
        // Not exercised by tests
        throw new UnsupportedOperationException("Not implemented for tests");
    }
}
