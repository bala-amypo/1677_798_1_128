package com.example.demo.service;

import com.example.demo.entity.RebalancingAlertRecord;

import java.util.List;

public interface RebalancingAlertService {

    RebalancingAlertRecord save(RebalancingAlertRecord alert);

    List<RebalancingAlertRecord> findByInvestor(Long investorId);
}
