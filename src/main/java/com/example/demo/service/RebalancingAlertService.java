package com.example.demo.Impl;

import com.example.demo.entity.RebalancingAlertRecord;
import java.util.List;

public interface RebalancingAlertService {
    List<RebalancingAlertRecord> checkForRebalancingAlerts(Long investorProfileId);
    List<RebalancingAlertRecord> getAlertsByInvestorProfile(Long investorProfileId);
    List<RebalancingAlertRecord> getUnresolvedAlerts();
    List<RebalancingAlertRecord> getAlertsBySeverity(String severity);
    RebalancingAlertRecord resolveAlert(Long alertId);
    void deleteAlert(Long alertId);
}