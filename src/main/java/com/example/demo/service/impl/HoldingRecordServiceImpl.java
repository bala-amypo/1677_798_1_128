package com.example.demo.service.impl;

import com.example.demo.entity.HoldingRecord;
import com.example.demo.entity.enums.AssetClassType;
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
    public HoldingRecord recordHolding(HoldingRecord holdingRecord) {
        return holdingRecordRepository.save(holdingRecord);
    }

    @Override
    public List<HoldingRecord> getHoldingsByInvestor(Long investorId) {
        return holdingRecordRepository.findByInvestorId(investorId);
    }

    @Override
    public HoldingRecord getHoldingById(Long id) {
        return holdingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("HoldingRecord not found with id: " + id));
    }

    @Override
    public List<HoldingRecord> getByInvestorAndAssetClass(Long investorId, AssetClassType assetClass) {
        return holdingRecordRepository.findByInvestorIdAndAssetClass(investorId, assetClass);
    }
}
