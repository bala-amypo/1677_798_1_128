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
    public HoldingRecord save(HoldingRecord holding) {
        holding.validate();
        return repository.save(holding);
    }

    @Override
    public List<HoldingRecord> getByInvestor(Long investorId) {
        return repository.findByInvestorId(investorId);
    }

    @Override
    public List<HoldingRecord> getHighValueHoldings(Double minValue) {
        return repository.findByValueGreaterThan(minValue);
    }
}
