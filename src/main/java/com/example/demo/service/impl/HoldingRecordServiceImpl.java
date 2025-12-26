package com.example.demo.service.impl;
import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class HoldingRecordServiceImpl {
    private final HoldingRecordRepository repository;
    public HoldingRecordServiceImpl(HoldingRecordRepository repository) { this.repository = repository; }
    public HoldingRecord recordHolding(HoldingRecord holding) {
        if (holding.getCurrentValue() <= 0) throw new IllegalArgumentException("value must be > 0");
        return repository.save(holding);
    }
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) { return repository.findByInvestorId(investorId); }
    public Optional<HoldingRecord> getHoldingById(Long id) { return repository.findById(id); }
}