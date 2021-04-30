package com.demo.creditscore.db.repository;

import com.demo.creditscore.db.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByFirstNameAndLastNameAndDateOfBirthEquals(String firstName, String lastName, LocalDate dateOfBirth);
}
