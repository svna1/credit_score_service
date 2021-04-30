package com.demo.creditscore.model;

import lombok.Data;

@Data
public class CreditScoreResponse {
    private CustomerDto customerDto;
    private Integer creditScore;
}
