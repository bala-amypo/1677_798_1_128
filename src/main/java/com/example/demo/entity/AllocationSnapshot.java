package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "allocation_snapshots")
public class AllocationSnapshot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime snapshotTimestamp;
    
    @Column(nullable = false)
    private Double totalPortfolioValue;
    
    @ElementCollection
    @CollectionTable(name = "snapshot_allocations", 
                     joinColumns = @JoinColumn(name = "snapshot_id"))
    @MapKeyColumn(name = "asset_class")
    @Column(name = "percentage")
    private Map<String, Double> assetAllocations;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "investor_profile_id", nullable = false)
    private InvestorProfile investorProfile;
    
    public AllocationSnapshot() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public LocalDateTime getSnapshotTimestamp() { return snapshotTimestamp; }
    public void setSnapshotTimestamp(LocalDateTime snapshotTimestamp) { this.snapshotTimestamp = snapshotTimestamp; }
    
    public Double getTotalPortfolioValue() { return totalPortfolioValue; }
    public void setTotalPortfolioValue(Double totalPortfolioValue) { this.totalPortfolioValue = totalPortfolioValue; }
    
    public Map<String, Double> getAssetAllocations() { return assetAllocations; }
    public void setAssetAllocations(Map<String, Double> assetAllocations) { this.assetAllocations = assetAllocations; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public InvestorProfile getInvestorProfile() { return investorProfile; }
    public void setInvestorProfile(InvestorProfile investorProfile) { this.investorProfile = investorProfile; }
}