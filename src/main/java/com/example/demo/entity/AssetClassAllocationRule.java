package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;

@Entity
@Table(name = "asset_class_allocation_rules")
public class AssetClassAllocationRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClassType assetClass;
    
    @Column(nullable = false)
    private Double targetPercentage;
    
    @Column(nullable = false)
    private Double tolerancePercentage;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    @ManyToOne
    @JoinColumn(name = "investor_profile_id", nullable = false)
    private InvestorProfile investorProfile;
    
    public AssetClassAllocationRule() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }
    
    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    
    public Double getTolerancePercentage() { return tolerancePercentage; }
    public void setTolerancePercentage(Double tolerancePercentage) { this.tolerancePercentage = tolerancePercentage; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public InvestorProfile getInvestorProfile() { return investorProfile; }
    public void setInvestorProfile(InvestorProfile investorProfile) { this.investorProfile = investorProfile; }
}