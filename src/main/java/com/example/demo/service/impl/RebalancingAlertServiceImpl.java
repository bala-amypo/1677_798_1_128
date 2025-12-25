package com.example.demo.service.impl;
import com.example.demo.entity.RebalancingAlertRecord;
import com.example.demo.repository.RebalancingAlertRecordRepository;
import com.example.demo.service.RebalancingAlertService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RebalancingAlertServiceImpl implements RebalancingAlertService {
    private final RebalancingAlertRecordRepository repo;
    public RebalancingAlertServiceImpl(RebalancingAlertRecordRepository repo) { this.repo = repo; }

    @Override
    public RebalancingAlertRecord createAlert(RebalancingAlertRecord alert) {
        if (!(alert.getCurrentPercentage() > alert.getTargetPercentage())) {
            throw new RuntimeException("currentPercentage > targetPercentage logic error");
        }
        return repo.save(alert);
    }

    @Override
    public RebalancingAlertRecord resolveAlert(Long id) {
        RebalancingAlertRecord alert = repo.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        alert.setResolved(true);
        return repo.save(alert);
    }

    @Override public List<RebalancingAlertRecord> getAlertsByInvestor(Long id) { return repo.findByInvestorId(id); }
    @Override public RebalancingAlertRecord getAlertById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("not found")); }
    @Override public List<RebalancingAlertRecord> getAllAlerts() { return repo.findAll(); }
}