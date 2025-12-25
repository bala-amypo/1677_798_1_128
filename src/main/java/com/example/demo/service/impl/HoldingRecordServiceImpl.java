package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class HoldingRecordServiceImpl {
    private final HoldingRecordRepository repo;

    public HoldingRecordServiceImpl(HoldingRecordRepository repo) {
        this.repo = repo;
    }

    public HoldingRecord recordHolding(HoldingRecord holding) {
        if (holding.getCurrentValue() <= 0) {
            throw new RuntimeException("must be > 0");
        }
        return repo.save(holding);
    }
}