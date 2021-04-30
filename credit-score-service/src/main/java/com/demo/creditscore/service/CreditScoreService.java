package com.demo.creditscore.service;

import com.demo.creditscore.model.CreditScoreResponse;
import com.demo.creditscore.model.CustomerDto;

import java.util.List;

public interface CreditScoreService {
    int CREDIT_SCORE_FACTOR = 1000;

    CreditScoreResponse retrieveCustomerCreditScore(CustomerDto customerDto);

    CustomerDto getCustomer(Long id);

    List<CustomerDto> findAllCustomers();

    void deleteCustomer(Long id);

    void updateCustomer(Long id, CustomerDto customerDto);
}
