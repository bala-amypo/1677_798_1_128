package com.example.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "investor_profiles")
public class InvestorProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", unique = true)
    private Long userId;
    
    @Column(name = "risk_tolerance")
    private String riskTolerance;
    
    @Column(name = "investment_horizon")
    private String investmentHorizon;
    
    @Column(name = "initial_investment")
    private BigDecimal initialInvestment;
    
    @Column(name = "current_investment")
    private BigDecimal currentInvestment;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Add these missing getters/setters
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getRiskTolerance() {
        return riskTolerance;
    }
    
    public void setRiskTolerance(String riskTolerance) {
        this.riskTolerance = riskTolerance;
    }
    
    public String getInvestmentHorizon() {
        return investmentHorizon;
    }
    
    public void setInvestmentHorizon(String investmentHorizon) {
        this.investmentHorizon = investmentHorizon;
    }
    
    public BigDecimal getInitialInvestment() {
        return initialInvestment;
    }
    
    public void setInitialInvestment(BigDecimal initialInvestment) {
        this.initialInvestment = initialInvestment;
    }
    
    public BigDecimal getCurrentInvestment() {
        return currentInvestment;
    }
    
    public void setCurrentInvestment(BigDecimal currentInvestment) {
        this.currentInvestment = currentInvestment;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    // Existing getters/setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}