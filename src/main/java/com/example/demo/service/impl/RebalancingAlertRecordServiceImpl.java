package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.RebalancingAlertRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebalancingAlertRecordServiceImpl implements RebalancingAlertRecordService {

    private final RebalancingAlertRecordRepository repository;

    public RebalancingAlertRecordServiceImpl(RebalancingAlertRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (alert.getCurrentPercentage() <= alert.getTargetPercentage()) {
            throw new RuntimeException("currentPercentage > targetPercentage");
        }
        return repository.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = getAlertById(id);
        alert.setResolved(true);
        return repository.save(alert);
    }

    @Override
    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public RebalancingAlertRecord getAlertById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<RebalancingAlertRecord> getAllAlerts() {
        return repository.findAll();
    }
}
