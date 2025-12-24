package com.example.demo.service;

import com.example.demo.entity.UserAccount;
import java.util.List;

public interface UserAccountService {
    UserAccount registerUser(UserAccount user);
    String authenticate(String username, String password);
    UserAccount getUserById(Long id);
    List<UserAccount> getAllUsers();
    UserAccount updateUserStatus(Long id, boolean active);
}