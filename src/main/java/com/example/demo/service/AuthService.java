package com.example.demo.service;

public interface AuthService {
    String register(String username, String email, String password, String role);
    String login(String username, String password);
}