package com.example.demo.Impl;

import com.example.demo.entity.User;

public interface AuthService {
    User registerUser(String username, String password, String email, String role);
    String authenticateUser(String username, String password);
    User getCurrentUser();
    void logoutUser();
}