package com.example.demo.service.impl;

import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvestorProfileServiceImpl {
    private final InvestorProfileRepository repository;

    public InvestorProfile createInvestor(InvestorProfile investor) {
        return repository.save(investor);
    }

    public InvestorProfile getInvestorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found with id: " + id));
    }

    public List<InvestorProfile> getAllInvestors() {
        return repository.findAll();
    }

    public InvestorProfile updateInvestorStatus(Long id, Boolean status) {
        InvestorProfile investor = getInvestorById(id);
        investor.setActive(status);
        return repository.save(investor);
    }

    public Optional<InvestorProfile> findByInvestorId(String investorId) {
        return repository.findByInvestorId(investorId);
    }
}