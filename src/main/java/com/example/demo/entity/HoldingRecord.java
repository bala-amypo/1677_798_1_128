// package com.example.demo.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "holding_records")
// public class HoldingRecord {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long investorId;

//     private String assetClass;

//     private Double quantity;

//     private Double currentValue;

//     public HoldingRecord() {
//     }

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

//     public String getAssetClass() {
//         return assetClass;
//     }

//     public void setAssetClass(String assetClass) {
//         this.assetClass = assetClass;
//     }

//     public Double getQuantity() {
//         return quantity;
//     }

//     public void setQuantity(Double quantity) {
//         this.quantity = quantity;
//     }

//     public Double getCurrentValue() {
//         return currentValue;
//     }

//     public void setCurrentValue(Double currentValue) {
//         this.currentValue = currentValue;
//     }
// }
