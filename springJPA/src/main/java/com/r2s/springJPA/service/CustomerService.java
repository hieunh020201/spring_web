package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateCustomerRequestDTO;
import com.r2s.springJPA.dto.request.UpdateCustomerRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.CustomerResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public interface CustomerService {
    public PageResponseDto getAllCustomers(Pageable pageable);

    public CustomerResponseDto insertCustomer(CreateCustomerRequestDTO requestDTO);

    public CustomerResponseDto updateCustomer(int customerId, UpdateCustomerRequestDto requestDto);

    public void deleteCustomer(int customerId);

    public CustomerResponseDto getCustomerById(int customerId);
}
