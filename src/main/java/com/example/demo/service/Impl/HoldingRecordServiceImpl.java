package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository repository;

    public HoldingRecordServiceImpl(HoldingRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        holding.validate();
        return repository.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public HoldingRecord getHoldingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<HoldingRecord> getAllHoldings() {
        return repository.findAll();
    }
}
