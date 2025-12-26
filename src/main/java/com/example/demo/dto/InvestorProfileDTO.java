package com.example.demo.dto;

import lombok.Data;

@Data
public class InvestorProfileDTO {
    private Long id;
    private String investorId;
    private String fullName;
    private String email;
    private Boolean active;
}