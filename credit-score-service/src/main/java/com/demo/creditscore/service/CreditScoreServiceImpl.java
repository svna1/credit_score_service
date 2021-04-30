package com.demo.creditscore.service;

import com.demo.creditscore.db.CreditScoreDbAdapter;
import com.demo.creditscore.db.entity.Customer;
import com.demo.creditscore.db.mapper.CustomerMapper;
import com.demo.creditscore.model.CreditScoreResponse;
import com.demo.creditscore.model.CustomerDto;
import com.demo.creditscore.service.enums.AgeRateEnum;
import com.demo.creditscore.service.enums.AnnualIncomeRateEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditScoreServiceImpl implements CreditScoreService {
    private final CreditScoreDbAdapter creditScoreDbAdapter;
    private final CustomerMapper customerMapper;

    @Override
    public CreditScoreResponse retrieveCustomerCreditScore(CustomerDto customerDto) {
        Optional<Customer> customerOptional = creditScoreDbAdapter.findCustomer(customerMapper.toEntity(customerDto));
        Integer rate;
        if (customerOptional.isPresent()) {
            rate = customerOptional.get().getCreditScore();
            customerDto.setId(customerOptional.get().getId());
        } else {
            rate = processNewCustomer(customerDto);
        }
        CreditScoreResponse creditScoreResponse = new CreditScoreResponse();
        creditScoreResponse.setCustomerDto(customerDto);
        creditScoreResponse.setCreditScore(rate);

        return creditScoreResponse;
    }

    @Override
    public CustomerDto getCustomer(Long id) {
        return customerMapper.toDto(creditScoreDbAdapter.getCustomer(id));
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerMapper.toDtoList(creditScoreDbAdapter.findAllCustomers());
    }

    @Override
    public void deleteCustomer(Long id) {
        creditScoreDbAdapter.deleteCustomer(id);
    }

    @Override
    public void updateCustomer(Long id, CustomerDto customerDto) {
        creditScoreDbAdapter.updateCustomer(id, customerDto);
    }

    private Integer processNewCustomer(CustomerDto customerDto) {
        Integer rate = (AnnualIncomeRateEnum.getAnnualIncomeRate(customerDto.getAnnualIncome()) + AgeRateEnum.getAgeRate(customerDto.getAge())) % CREDIT_SCORE_FACTOR;
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setCreditScore(rate);
        customerDto.setId(creditScoreDbAdapter.saveCustomer(customer).getId());

        return rate;
    }

}
