package com.demo.creditscore.db;

import com.demo.creditscore.db.entity.Customer;
import com.demo.creditscore.db.mapper.CustomerMapper;
import com.demo.creditscore.db.repository.CustomerRepository;
import com.demo.creditscore.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreditScoreDbAdapter {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private static final String CUSTOMER_NOT_FOUND = "Customer with id= %s not found";

    public Optional<Customer> findCustomer(Customer customer) {
        return customerRepository.findCustomerByFirstNameAndLastNameAndDateOfBirthEquals(customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth());
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format(CUSTOMER_NOT_FOUND, id)));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public void updateCustomer(Long id, CustomerDto customerDto) {
        customerMapper.updateEntityFromDto(customerDto, getCustomer(id));
    }

}
