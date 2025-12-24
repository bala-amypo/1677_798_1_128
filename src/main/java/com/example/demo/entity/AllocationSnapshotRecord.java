package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AllocationSnapshotRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;
    private double totalPortfolioValue;

    @Column(columnDefinition = "TEXT")
    private String allocationJson;

    public Long getId() {
        return id;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public void setTotalPortfolioValue(double totalPortfolioValue) {
        this.totalPortfolioValue = totalPortfolioValue;
    }

    public void setAllocationJson(String allocationJson) {
        this.allocationJson = allocationJson;
    }
}
