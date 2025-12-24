// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "allocation_snapshots")
// public class AllocationSnapshotRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long investorId;

//     private double totalPortfolioValue;

//     @Column(columnDefinition = "TEXT")
//     private String allocationJson;

//     // ===== Getters & Setters =====

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public Long getInvestorId() {
//         return investorId;
//     }

//     public void setInvestorId(Long investorId) {
//         this.investorId = investorId;
//     }

//     public double getTotalPortfolioValue() {
//         return totalPortfolioValue;
//     }

//     public void setTotalPortfolioValue(double totalPortfolioValue) {
//         this.totalPortfolioValue = totalPortfolioValue;
//     }

//     public String getAllocationJson() {
//         return allocationJson;
//     }

//     public void setAllocationJson(String allocationJson) {
//         this.allocationJson = allocationJson;
//     }
// }
