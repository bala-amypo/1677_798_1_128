package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // CRITICAL: This tells Spring to create the bean for this service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository holdingRecordRepository;

    // CONSTRUCTOR INJECTION: Strictly required by your technical constraints
    public HoldingRecordServiceImpl(HoldingRecordRepository holdingRecordRepository) {
        this.holdingRecordRepository = holdingRecordRepository;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        // Validation with specific error message keyword required for tests
        if (holding.getCurrentValue() <= 0) {
            throw new RuntimeException("must be > 0");
        }
        return holdingRecordRepository.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return holdingRecordRepository.findByInvestorId(investorId);
    }

    @Override
    public HoldingRecord getHoldingById(Long id) {
        // Validation with specific error message keyword required for tests
        return holdingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<HoldingRecord> getAllHoldings() {
        return holdingRecordRepository.findAll();
    }
}