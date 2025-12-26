package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
public class InvestorProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String investorId;
    private String fullName;
    private String email;
    private Boolean active;

    public InvestorProfile() {}
    public InvestorProfile(String investorId, String fullName, String email, Boolean active) {
        this.investorId = investorId;
        this.fullName = fullName;
        this.email = email;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public String getInvestorId() { return investorId; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}