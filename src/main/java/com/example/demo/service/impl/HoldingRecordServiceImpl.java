package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
import com.example.demo.repository.HoldingRecordRepository;
import com.example.demo.service.HoldingRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldingRecordServiceImpl implements HoldingRecordService {

    private final HoldingRecordRepository holdingRecordRepository;

    public HoldingRecordServiceImpl(HoldingRecordRepository holdingRecordRepository) {
        this.holdingRecordRepository = holdingRecordRepository;
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        // Returns a List directly from repository
        return holdingRecordRepository.findByInvestorId(investorId);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestorAndAssetClass(Long investorId, AssetClassType assetClass) {
        // Returns a List directly from repository
        return holdingRecordRepository.findByInvestorIdAndAssetClass(investorId, assetClass);
    }

    @Override
    public HoldingRecord createHolding(HoldingRecord holding) {
        return holdingRecordRepository.save(holding);
    }

    @Override
    public List<HoldingRecord> getHoldingsGreaterThan(double value) {
        return holdingRecordRepository.findByCurrentValueGreaterThan(value);
    }
}
