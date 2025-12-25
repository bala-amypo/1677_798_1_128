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
    @Enumerated(EnumType.STRING) private AssetClassType assetClass;
    private Double currentPercentage;
    private Double targetPercentage;
    @Enumerated(EnumType.STRING) private AlertSeverity severity;
    private String message;
    private LocalDateTime alertDate = LocalDateTime.now();
    private Boolean resolved = false;
}