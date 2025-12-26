package com.example.demo.entity;
import com.example.demo.entity.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RebalancingAlertRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    private Double currentPercentage;
    private Double targetPercentage;
    private Boolean resolved = false;

    public RebalancingAlertRecord() {}
    public RebalancingAlertRecord(Long investorId, AssetClassType type, Double current, Double target, AlertSeverity severity, String msg, LocalDateTime date, Boolean resolved) {
        this.investorId = investorId;
        this.currentPercentage = current;
        this.targetPercentage = target;
        this.resolved = resolved;
    }

    public Double getCurrentPercentage() { return currentPercentage; }
    public Double getTargetPercentage() { return targetPercentage; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
    public void setId(Long id) { this.id = id; }
}