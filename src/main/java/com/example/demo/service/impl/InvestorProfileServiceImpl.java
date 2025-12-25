package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InvestorProfileServiceImpl implements InvestorProfileService {
    private final InvestorProfileRepository repo;

    public InvestorProfileServiceImpl(InvestorProfileRepository repo) {
        this.repo = repo;
    }

    @Override
    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repo.save(investor);
    }

    @Override
    public InvestorProfile getInvestorById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Investor not found"));
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return repo.findAll();
    }
}