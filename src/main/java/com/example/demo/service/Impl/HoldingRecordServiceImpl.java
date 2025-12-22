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
        HoldingRecord existing = getHoldingById(id);
        if (existing != null) {
            // Update fields as needed
            existing.setInvestorId(holding.getInvestorId());
            existing.setAssetName(holding.getAssetName());
            existing.setQuantity(holding.getQuantity());
            existing.setPricePerUnit(holding.getPricePerUnit());
            existing.setRecordDate(holding.getRecordDate());
            return holdingRecordRepository.save(existing);
        }
        return null;
    }
    
    @Override
    public void deleteHolding(Long id) {
        holdingRecordRepository.deleteById(id);
    }
}