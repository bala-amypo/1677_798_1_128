package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface AuthService {

    UserAccount register(UserAccount user);

    String login(String username, String password);
}
