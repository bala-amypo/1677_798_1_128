package com.example.demo.dto;

import com.example.demo.entity.enums.RoleType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private RoleType role;
}