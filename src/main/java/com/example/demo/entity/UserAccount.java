package com.example.demo.entity;

import com.example.demo.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_accounts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public UserAccount(String username, String email, String password, RoleType role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}