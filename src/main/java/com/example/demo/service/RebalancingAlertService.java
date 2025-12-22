package com.example.demo.service;

import com.example.demo.entity.RebalancingAlert;
import java.util.List;

public interface RebalancingAlertService {
    RebalancingAlert createAlert(RebalancingAlert alert);
    RebalancingAlert getAlertById(Long id);
    List<RebalancingAlert> getAlertsByInvestor(Long investorId);
    List<RebalancingAlert> getAlertsByInvestorAndResolved(Long investorId, boolean resolved);
    List<RebalancingAlert> getAllAlerts();
    List<RebalancingAlert> getUnresolvedAlerts();
    RebalancingAlert updateAlert(Long id, RebalancingAlert alert);
    RebalancingAlert resolveAlert(Long id);
    void deleteAlert(Long id);
    List<RebalancingAlert> getRecentAlerts(int days);
}