package com.demo.creditscore.controller;

import com.demo.creditscore.db.entity.Customer;
import com.demo.creditscore.model.CreditScoreResponse;
import com.demo.creditscore.model.CustomerDto;
import com.demo.creditscore.service.CreditScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditScoreController.class)
@ExtendWith(SpringExtension.class)
public class CreditScoreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditScoreService creditScoreService;

    @Test
    @SneakyThrows
    public void getCustomer() {
        when(this.creditScoreService.getCustomer(anyLong())).thenReturn(new CustomerDto());
        this.mockMvc.perform(get("/api/credit-score/customers/1")).andDo(print()).andExpect(status().isOk());

        verify(creditScoreService, times(1)).getCustomer(anyLong());
        verifyNoMoreInteractions(creditScoreService);
    }

    @Test
    @SneakyThrows
    public void testFindAll() {
        String name = "Test";
        List<CustomerDto> content = Collections.singletonList(createCustomerDto(name, name));
        when(this.creditScoreService.findAllCustomers()).thenReturn(content);

        this.mockMvc.perform(get("/api/credit-score/customers")).andExpect(status().isOk()).andExpect(jsonPath("*").isArray())
                .andExpect(jsonPath("[0].firstName").value(equalTo(name))).andDo(print()).andReturn();

        verify(creditScoreService, times(1)).findAllCustomers();
    }

    @Test
    @SneakyThrows
    public void testUpdate() {
        String name = "Test";
        doNothing().when(this.creditScoreService).updateCustomer(any(), any());
        this.mockMvc
                .perform(put("/api/credit-score/customers/1").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(createCustomerDto(name, name))).with(csrf()))
                .andDo(print()).andExpect(status().isOk());

        verify(creditScoreService, times(1)).updateCustomer(any(), any());
        verifyNoMoreInteractions(creditScoreService);
    }

    @Test
    @SneakyThrows
    public void testCalculateOrGetCustomerCreditScore() {
        String name = "Test";
        Integer rate = 800;
        CustomerDto dto = createCustomerDto(name, name);
        when(this.creditScoreService.retrieveCustomerCreditScore(dto)).thenReturn(creditScoreResponse(dto, rate));
        MvcResult result = this.mockMvc
                .perform(put("/api/credit-score/customers").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(dto)).with(csrf()))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains(rate.toString()));
        verify(creditScoreService, times(1)).retrieveCustomerCreditScore(dto);
        verifyNoMoreInteractions(creditScoreService);
    }

    @Test
    @SneakyThrows
    public void testDelete() {
        String name = "Test";
        doNothing().when(this.creditScoreService).deleteCustomer(any());
        this.mockMvc
                .perform(delete("/api/credit-score/customers/1").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(createCustomerDto(name, name))).with(csrf()))
                .andDo(print()).andExpect(status().isOk());

        verify(creditScoreService, times(1)).deleteCustomer(any());
        verifyNoMoreInteractions(creditScoreService);
    }

    private CustomerDto createCustomerDto(String name, String surname) {
        CustomerDto dto = new CustomerDto();
        dto.setAge(18);
        dto.setFirstName(name);
        dto.setLastName(surname);
        dto.setAnnualIncome(3000);
        dto.setDateOfBirth(LocalDate.now());

        return dto;
    }

    private CreditScoreResponse creditScoreResponse(CustomerDto customerDto, Integer creditScore) {
        CreditScoreResponse creditScoreResponse = new CreditScoreResponse();
        creditScoreResponse.setCustomerDto(customerDto);
        creditScoreResponse.setCreditScore(creditScore);

        return creditScoreResponse;
    }
}
