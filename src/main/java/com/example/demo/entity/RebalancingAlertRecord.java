package com.example.demo.entity;

import com.example.demo.entity.enums.AlertSeverity;
import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rebalancing_alerts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RebalancingAlertRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentPercentage;
    private Double targetPercentage;

    @Enumerated(EnumType.STRING)
    private AlertSeverity severity;

    private String message;
    private LocalDateTime alertDate;
    private Boolean resolved = false;

    public RebalancingAlertRecord(Long investorId, AssetClassType assetClass, Double currentPercentage, Double targetPercentage, AlertSeverity severity, String message, LocalDateTime alertDate, Boolean resolved) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentPercentage = currentPercentage;
        this.targetPercentage = targetPercentage;
        this.severity = severity;
        this.message = message;
        this.alertDate = alertDate;
        this.resolved = resolved;
    }
}