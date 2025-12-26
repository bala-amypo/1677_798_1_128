// src/main/java/com/example/demo/security/CustomUserDetailsService.java
package com.example.demo.security;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository repo;

    public CustomUserDetailsService(UserAccountRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
        GrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                Collections.singletonList(authority));
    }
}
