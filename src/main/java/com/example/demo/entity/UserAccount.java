package com.example.demo.entity;

import com.example.demo.entity.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    }
)
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private Boolean active = true;

    // getters & setters
}
