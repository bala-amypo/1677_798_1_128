package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SnapshotDTO {
    private Long investorId;
    private Double totalPortfolioValue;
    private LocalDateTime snapshotDate;
    private String allocationJson;
}