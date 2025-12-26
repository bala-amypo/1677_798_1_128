// src/main/java/com/example/demo/service/impl/RebalancingAlertServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {

    private final RebalancingAlertRecordRepository repo;

    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (alert.getCurrentPercentage() <= alert.getTargetPercentage()) {
            throw new IllegalArgumentException("currentPercentage > targetPercentage");
        }
        return repo.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord a = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
        a.setResolved(true);
        return repo.save(a);
    }

    @Override
    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public RebalancingAlertRecord getAlertById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
    }

    @Override
    public List<RebalancingAlertRecord> getAllAlerts() {
        return repo.findAll();
    }
}
