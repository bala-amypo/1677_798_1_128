// src/main/java/com/example/demo/service/impl/InvestorProfileServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import com.example.demo.service.InvestorProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
    }

    @Override
    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repo.findByInvestorId(investorId);
    }

    @Override
    public List<InvestorProfile> getAllInvestors() {
        return repo.findAll();
    }

    @Override
    public InvestorProfile updateInvestorStatus(Long id, boolean active) {
        InvestorProfile p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found: " + id));
        p.setActive(active);
        return repo.save(p);
    }
}
