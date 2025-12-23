package com.example.demo.entity;

import com.example.demo.entity.enums.RoleType;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private InvestorProfile investorProfile;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<HoldingRecord> holdingRecords;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AllocationSnapshot> allocationSnapshots;
    
    public User() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public RoleType getRole() { return role; }
    public void setRole(RoleType role) { this.role = role; }
    
    public InvestorProfile getInvestorProfile() { return investorProfile; }
    public void setInvestorProfile(InvestorProfile investorProfile) { this.investorProfile = investorProfile; }
    
    public Set<HoldingRecord> getHoldingRecords() { return holdingRecords; }
    public void setHoldingRecords(Set<HoldingRecord> holdingRecords) { this.holdingRecords = holdingRecords; }
    
    public Set<AllocationSnapshot> getAllocationSnapshots() { return allocationSnapshots; }
    public void setAllocationSnapshots(Set<AllocationSnapshot> allocationSnapshots) { this.allocationSnapshots = allocationSnapshots; }
}