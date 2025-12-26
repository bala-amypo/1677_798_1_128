package com.example.demo.entity;
import com.example.demo.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class RebalancingAlertRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long investorId;
    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;
    private Double currentPercentage;
    private Double targetPercentage;
    @Enumerated(EnumType.STRING)
    private AlertSeverity severity;
    private String message;
    private LocalDateTime timestamp;
    private Boolean resolved;

    public RebalancingAlertRecord(Long investorId, AssetClassType assetClass, Double currentPct, Double targetPct, AlertSeverity severity, String msg, LocalDateTime ts, Boolean resolved) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentPercentage = currentPct;
        this.targetPercentage = targetPct;
        this.severity = severity;
        this.message = msg;
        this.timestamp = ts;
        this.resolved = resolved;
    }
}