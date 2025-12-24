package com.example.demo.service.impl;

import com.example.demo.entity.AssetClassAllocationRule;
import com.example.demo.repository.AssetClassAllocationRuleRepository;
import com.example.demo.service.AssetClassAllocationRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetClassAllocationRuleServiceImpl
        implements AssetClassAllocationRuleService {

    private final AssetClassAllocationRuleRepository repository;

    public AssetClassAllocationRuleServiceImpl(
            AssetClassAllocationRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetClassAllocationRule> getAllRules() {
        return repository.findAll();
    }
}
