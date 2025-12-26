// src/main/java/com/example/demo/service/RebalancingAlertService.java
package com.example.demo.service;

import com.example.demo.entity.RebalancingAlertRecord;

import java.util.List;

public interface RebalancingAlertService {
    RebalancingAlertRecord createAlert(RebalancingAlertRecord record);
    RebalancingAlertRecord resolveAlert(Long id);
    List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId);
}
