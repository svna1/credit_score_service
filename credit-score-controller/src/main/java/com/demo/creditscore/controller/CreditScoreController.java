package com.demo.creditscore.controller;

import com.demo.creditscore.model.CreditScoreResponse;
import com.demo.creditscore.model.CustomerDto;
import com.demo.creditscore.service.CreditScoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/credit-score/customers")
public class CreditScoreController {

    private final CreditScoreService creditScoreService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(creditScoreService.getCustomer(id));
    }

    @PutMapping
    public ResponseEntity<CreditScoreResponse> calculateOrGetCustomerCreditScore(@RequestBody @Valid CustomerDto customerDto) {
        return ResponseEntity.ok(creditScoreService.retrieveCustomerCreditScore(customerDto));
    }

    @PutMapping(path = "/{id}")
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid CustomerDto customerDto) {
        creditScoreService.updateCustomer(id, customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAllCustomers() {
        return ResponseEntity.ok(creditScoreService.findAllCustomers());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        creditScoreService.deleteCustomer(id);
    }

}
