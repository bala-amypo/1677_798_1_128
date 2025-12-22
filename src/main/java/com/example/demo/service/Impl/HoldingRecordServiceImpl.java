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

    private final HoldingRecordRepository holdingRecordRepository;

    public HoldingRecordServiceImpl(HoldingRecordRepository holdingRecordRepository) {
        this.holdingRecordRepository = holdingRecordRepository;
    }

    @Override
    public HoldingRecord recordHolding(HoldingRecord holding) {
        return holdingRecordRepository.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return holdingRecordRepository.findByInvestorId(investorId);
    }

    @Override
    public HoldingRecord getHoldingById(Long id) {
        return holdingRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<HoldingRecord> getAllHoldings() {
        return holdingRecordRepository.findAll();
    }
    
    @Override
    public HoldingRecord updateHolding(Long id, HoldingRecord holding) {
        // Simplified: Just save the new holding with the given ID
        holding.setId(id);
        return holdingRecordRepository.save(holding);
    }
    
    @Override
    public void deleteHolding(Long id) {
        holdingRecordRepository.deleteById(id);
    }
}