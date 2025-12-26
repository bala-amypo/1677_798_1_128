// src/main/java/com/example/demo/dto/SnapshotDTO.java
package com.example.demo.dto;

import java.time.LocalDateTime;

public class SnapshotDTO {

    private Long id;
    private Long investorId;
    private LocalDateTime snapshotDate;
    private Double totalPortfolioValue;
    private String allocationJson;

    public SnapshotDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public LocalDateTime getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDateTime snapshotDate) {
        this.snapshotDate = snapshotDate;
    }

    public Double getTotalPortfolioValue() {
        return totalPortfolioValue;
    }

    public void setTotalPortfolioValue(Double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public String getAllocationJson() {
        return allocationJson;
    }

    public void setAllocationJson(String allocationJson) {
        this.allocationJson = allocationJson;
    }
}
