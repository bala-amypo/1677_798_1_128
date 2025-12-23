package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rebalancing_alerts")
public class RebalancingAlert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "portfolio_id")
    private Long portfolioId;
    
    @Column(name = "alert_type")
    private String alertType;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "current_allocation")
    private String currentAllocation;
    
    @Column(name = "target_allocation")
    private String targetAllocation;
    
    @Column(name = "deviation_percentage")
    private Double deviationPercentage;
    
    @Column(name = "is_read")
    private Boolean isRead = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public Long getPortfolioId() {
        return portfolioId;
    }
    
    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    public String getAlertType() {
        return alertType;
    }
    
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getCurrentAllocation() {
        return currentAllocation;
    }
    
    public void setCurrentAllocation(String currentAllocation) {
        this.currentAllocation = currentAllocation;
    }
    
    public String getTargetAllocation() {
        return targetAllocation;
    }
    
    public void setTargetAllocation(String targetAllocation) {
        this.targetAllocation = targetAllocation;
    }
    
    public Double getDeviationPercentage() {
        return deviationPercentage;
    }
    
    public void setDeviationPercentage(Double deviationPercentage) {
        this.deviationPercentage = deviationPercentage;
    }
    
    public Boolean getIsRead() {
        return isRead;
    }
    
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
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