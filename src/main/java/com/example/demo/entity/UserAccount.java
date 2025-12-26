package com.example.demo.entity;
import com.example.demo.entity.enums.RoleType;
import jakarta.persistence.*;

@Entity
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public UserAccount() {}
    public UserAccount(String username, String email, String password, RoleType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public RoleType getRole() { return role; }
}