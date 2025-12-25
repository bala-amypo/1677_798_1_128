package com.example.demo.entity;
import com.example.demo.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) private String username;
    @Column(unique = true) private String email;
    private String password;
    @Enumerated(EnumType.STRING) private RoleType role;
    private Boolean active = true;
} 