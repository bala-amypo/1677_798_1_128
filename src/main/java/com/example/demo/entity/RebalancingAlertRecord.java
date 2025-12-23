package com.example.demo.entity;

import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rebalancing_alert_records")
public class RebalancingAlertRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetClassType assetClass;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AlertSeverity severity;
    
    @Column(nullable = false)
    private String message;
    
    @Column(nullable = false)
    private Double currentPercentage;
    
    @Column(nullable = false)
    private Double targetPercentage;
    
    @Column(nullable = false)
    private Double driftAmount;
    
    @Column(nullable = false)
    private LocalDateTime alertTimestamp;
    
    @Column(nullable = false)
    private Boolean isResolved = false;
    
    @ManyToOne
    @JoinColumn(name = "investor_profile_id", nullable = false)
    private InvestorProfile investorProfile;
    
    @ManyToOne
    @JoinColumn(name = "snapshot_id", nullable = false)
    private AllocationSnapshot allocationSnapshot;
    
    public RebalancingAlertRecord() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public AssetClassType getAssetClass() { return assetClass; }
    public void setAssetClass(AssetClassType assetClass) { this.assetClass = assetClass; }
    
    public AlertSeverity getSeverity() { return severity; }
    public void setSeverity(AlertSeverity severity) { this.severity = severity; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public Double getCurrentPercentage() { return currentPercentage; }
    public void setCurrentPercentage(Double currentPercentage) { this.currentPercentage = currentPercentage; }
    
    public Double getTargetPercentage() { return targetPercentage; }
    public void setTargetPercentage(Double targetPercentage) { this.targetPercentage = targetPercentage; }
    
    public Double getDriftAmount() { return driftAmount; }
    public void setDriftAmount(Double driftAmount) { this.driftAmount = driftAmount; }
    
    public LocalDateTime getAlertTimestamp() { return alertTimestamp; }
    public void setAlertTimestamp(LocalDateTime alertTimestamp) { this.alertTimestamp = alertTimestamp; }
    
    public Boolean getIsResolved() { return isResolved; }
    public void setIsResolved(Boolean isResolved) { this.isResolved = isResolved; }
    
    public InvestorProfile getInvestorProfile() { return investorProfile; }
    public void setInvestorProfile(InvestorProfile investorProfile) { this.investorProfile = investorProfile; }
    
    public AllocationSnapshot getAllocationSnapshot() { return allocationSnapshot; }
    public void setAllocationSnapshot(AllocationSnapshot allocationSnapshot) { this.allocationSnapshot = allocationSnapshot; }
}