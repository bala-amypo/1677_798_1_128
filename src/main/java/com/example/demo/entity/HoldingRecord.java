package com.example.demo.entity;
import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class HoldingRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    private Double currentValue;

    public HoldingRecord() {}
    public HoldingRecord(Long investorId, AssetClassType type, Double value, LocalDateTime date) {
        this.investorId = investorId;
        this.currentValue = value;
    }

    public Double getCurrentValue() { return currentValue; }
    public void setCurrentValue(Double val) { this.currentValue = val; }
    public Long getInvestorId() { return investorId; }
    public void setId(Long id) { this.id = id; }
}