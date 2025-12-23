package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlert;
import com.example.demo.repository.RebalancingAlertRepository;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {
    
    @Autowired
    private RebalancingAlertRepository rebalancingAlertRepository;
    
    @Override
    @Transactional
    public RebalancingAlert createAlert(Long userId, Long portfolioId, String alertType, 
                                       String message, String currentAllocation, 
                                       String targetAllocation, Double deviationPercentage) {
        RebalancingAlert alert = new RebalancingAlert();
        alert.setUserId(userId);
        alert.setPortfolioId(portfolioId);
        alert.setAlertType(alertType);
        alert.setMessage(message);
        alert.setCurrentAllocation(currentAllocation);
        alert.setTargetAllocation(targetAllocation);
        alert.setDeviationPercentage(deviationPercentage);
        alert.setIsRead(false);
        alert.setCreatedAt(LocalDateTime.now());
        alert.setUpdatedAt(LocalDateTime.now());
        
        return rebalancingAlertRepository.save(alert);
    }
    
    @Override
    public List<RebalancingAlert> getAlertsByUserId(Long userId) {
        return rebalancingAlertRepository.findByUserId(userId);
    }
    
    @Override
    public List<RebalancingAlert> getUnreadAlertsByUserId(Long userId) {
        return rebalancingAlertRepository.findByUserIdAndIsRead(userId, false);
    }
    
    @Override
    public List<RebalancingAlert> getAlertsByPortfolioId(Long portfolioId) {
        return rebalancingAlertRepository.findByPortfolioId(portfolioId);
    }
    
    @Override
    @Transactional
    public void markAlertAsRead(Long alertId) {
        RebalancingAlert alert = rebalancingAlertRepository.findById(alertId)
                .orElseThrow(() -> new RuntimeException("Alert not found with ID: " + alertId));
        alert.setIsRead(true);
        alert.setUpdatedAt(LocalDateTime.now());
        rebalancingAlertRepository.save(alert);
    }
    
    @Override
    @Transactional
    public void markAllAlertsAsRead(Long userId) {
        List<RebalancingAlert> alerts = rebalancingAlertRepository.findByUserIdAndIsRead(userId, false);
        alerts.forEach(alert -> {
            alert.setIsRead(true);
            alert.setUpdatedAt(LocalDateTime.now());
        });
        rebalancingAlertRepository.saveAll(alerts);
    }
    
    @Override
    @Transactional
    public void deleteAlert(Long alertId) {
        if (!rebalancingAlertRepository.existsById(alertId)) {
            throw new RuntimeException("Alert not found with ID: " + alertId);
        }
        rebalancingAlertRepository.deleteById(alertId);
    }
    
    @Override
    @Transactional
    public void deleteAlertsByPortfolioId(Long portfolioId) {
        List<RebalancingAlert> alerts = rebalancingAlertRepository.findByPortfolioId(portfolioId);
        rebalancingAlertRepository.deleteAll(alerts);
    }
}