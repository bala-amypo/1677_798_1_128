package com.example.demo.service;

import com.example.demo.entity.RebalancingAlert;
import java.util.List;

public interface RebalancingAlertService {
    RebalancingAlert createAlert(Long userId, Long portfolioId, String alertType, 
                                String message, String currentAllocation, 
                                String targetAllocation, Double deviationPercentage);
    
    List<RebalancingAlert> getAlertsByUserId(Long userId);
    
    List<RebalancingAlert> getUnreadAlertsByUserId(Long userId);
    
    List<RebalancingAlert> getAlertsByPortfolioId(Long portfolioId);
    
    void markAlertAsRead(Long alertId);
    
    void markAllAlertsAsRead(Long userId);
    
    void deleteAlert(Long alertId);
    
    void deleteAlertsByPortfolioId(Long portfolioId);
}