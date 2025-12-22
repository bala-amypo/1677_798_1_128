package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlert;
import com.example.demo.repository.RebalancingAlertRepository;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RebalancingAlertServiceImpl implements RebalancingAlertService {

    private final RebalancingAlertRepository rebalancingAlertRepository;

    public RebalancingAlertServiceImpl(RebalancingAlertRepository rebalancingAlertRepository) {
        this.rebalancingAlertRepository = rebalancingAlertRepository;
    }

    @Override
    public RebalancingAlert createAlert(RebalancingAlert alert) {
        alert.setAlertDate(LocalDate.now());
        alert.setResolved(false);
        return rebalancingAlertRepository.save(alert);
    }

    @Override
    public RebalancingAlert getAlertById(Long id) {
        return rebalancingAlertRepository.findById(id).orElse(null);
    }

    @Override
    public List<RebalancingAlert> getAlertsByInvestor(Long investorId) {
        return rebalancingAlertRepository.findByInvestorId(investorId);
    }

    @Override
    public List<RebalancingAlert> getAlertsByInvestorAndResolved(Long investorId, boolean resolved) {
        return rebalancingAlertRepository.findByInvestorIdAndResolved(investorId, resolved);
    }

    @Override
    public List<RebalancingAlert> getAllAlerts() {
        return rebalancingAlertRepository.findAll();
    }

    @Override
    public List<RebalancingAlert> getUnresolvedAlerts() {
        return rebalancingAlertRepository.findByResolved(false);
    }

    @Override
    public RebalancingAlert updateAlert(Long id, RebalancingAlert alert) {
        RebalancingAlert existing = getAlertById(id);
        if (existing != null) {
            existing.setInvestorId(alert.getInvestorId());
            existing.setAssetClass(alert.getAssetClass());
            existing.setCurrentPercentage(alert.getCurrentPercentage());
            existing.setTargetPercentage(alert.getTargetPercentage());
            existing.setDeviation(alert.getDeviation());
            existing.setMessage(alert.getMessage());
            existing.setActionType(alert.getActionType());
            existing.setRequiredActionAmount(alert.getRequiredActionAmount());
            return rebalancingAlertRepository.save(existing);
        }
        return null;
    }

    @Override
    public RebalancingAlert resolveAlert(Long id) {
        RebalancingAlert alert = getAlertById(id);
        if (alert != null) {
            alert.setResolved(true);
            alert.setResolvedDate(LocalDate.now());
            return rebalancingAlertRepository.save(alert);
        }
        return null;
    }

    @Override
    public void deleteAlert(Long id) {
        rebalancingAlertRepository.deleteById(id);
    }

    @Override
    public List<RebalancingAlert> getRecentAlerts(int days) {
        LocalDate cutoffDate = LocalDate.now().minusDays(days);
        return rebalancingAlertRepository.findByAlertDateAfter(cutoffDate);
    }
}