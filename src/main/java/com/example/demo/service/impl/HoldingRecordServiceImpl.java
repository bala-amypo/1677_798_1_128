// src/main/java/com/example/demo/service/impl/HoldingRecordServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository repo;

    public HoldingRecordServiceImpl(HoldingRecordRepository repo) {
        this.repo = repo;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        if (holding.getCurrentValue() == null || holding.getCurrentValue() <= 0) {
            throw new IllegalArgumentException("must be > 0");
        }
        return repo.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public Optional<HoldingRecord> getHoldingById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<HoldingRecord> getAllHoldings() {
        return repo.findAll();
    }
}
