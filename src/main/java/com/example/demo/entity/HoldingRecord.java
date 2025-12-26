package com.example.demo.entity;

import com.example.demo.entity.enums.AssetClassType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "holding_records")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HoldingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long investorId;

    @Enumerated(EnumType.STRING)
    private AssetClassType assetClass;

    private Double currentValue;
    private LocalDateTime recordDate;

    public HoldingRecord(Long investorId, AssetClassType assetClass, Double currentValue, LocalDateTime recordDate) {
        this.investorId = investorId;
        this.assetClass = assetClass;
        this.currentValue = currentValue;
        this.recordDate = recordDate;
    }
}