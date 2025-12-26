package com.example.demo.service.impl;
import com.example.demo.entity.InvestorProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InvestorProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvestorProfileServiceImpl {
    private final InvestorProfileRepository repository;
    public InvestorProfileServiceImpl(InvestorProfileRepository repository) { this.repository = repository; }
    public InvestorProfile createInvestor(InvestorProfile inv) { return repository.save(inv); }
    public InvestorProfile getInvestorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }
    public List<InvestorProfile> getAllInvestors() { return repository.findAll(); }
    public InvestorProfile updateInvestorStatus(Long id, Boolean status) {
        InvestorProfile inv = getInvestorById(id);
        inv.setActive(status);
        return repository.save(inv);
    }
    public Optional<InvestorProfile> findByInvestorId(String id) { return repository.findByInvestorId(id); }
}