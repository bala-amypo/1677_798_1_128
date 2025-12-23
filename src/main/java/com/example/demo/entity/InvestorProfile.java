package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "investor_profiles")
public class InvestorProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String investorId;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @OneToMany(mappedBy = "investorProfile", cascade = CascadeType.ALL)
    private Set<AssetClassAllocationRule> allocationRules;
    
    @OneToMany(mappedBy = "investorProfile", cascade = CascadeType.ALL)
    private Set<AllocationSnapshot> allocationSnapshots;
    
    @OneToMany(mappedBy = "investorProfile", cascade = CascadeType.ALL)
    private Set<RebalancingAlertRecord> alertRecords;
    
    public InvestorProfile() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getInvestorId() { return investorId; }
    public void setInvestorId(String investorId) { this.investorId = investorId; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public Set<AssetClassAllocationRule> getAllocationRules() { return allocationRules; }
    public void setAllocationRules(Set<AssetClassAllocationRule> allocationRules) { this.allocationRules = allocationRules; }
    
    public Set<AllocationSnapshot> getAllocationSnapshots() { return allocationSnapshots; }
    public void setAllocationSnapshots(Set<AllocationSnapshot> allocationSnapshots) { this.allocationSnapshots = allocationSnapshots; }
    
    public Set<RebalancingAlertRecord> getAlertRecords() { return alertRecords; }
    public void setAlertRecords(Set<RebalancingAlertRecord> alertRecords) { this.alertRecords = alertRecords; }
}