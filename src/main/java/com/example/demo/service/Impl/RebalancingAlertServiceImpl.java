package com.example.demo.service.impl;

import com.example.demo.entity.RebalancingAlertRecord;
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

    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        alert.validate();
        return repo.save(alert);
    }

    public void resolveAlert(Long id) {
        RebalancingAlertRecord a = getAlertById(id);
        a.setResolved(true);
        repo.save(a);
    }

    public List<RebalancingAlertRecord> getAlertsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    public RebalancingAlertRecord getAlertById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<RebalancingAlertRecord> getAllAlerts() {
        return repo.findAll();
    }
}
