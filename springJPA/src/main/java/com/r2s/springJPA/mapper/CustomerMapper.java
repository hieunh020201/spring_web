package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.AddressByCustomerResponseDto;
import com.r2s.springJPA.dto.response.CustomerResponseDto;
import com.r2s.springJPA.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    public List<CustomerResponseDto> convertEntitiesResponseDtos(List<Customer> users) {
        return users.stream().map(this::convertEntityResponseDto).toList();
    }

    public CustomerResponseDto convertEntityResponseDto(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(customer, customerResponseDto);
        return customerResponseDto;
    }

    public AddressByCustomerResponseDto convertEntityResponseDtoV2(Customer customer) {
        AddressByCustomerResponseDto addressByCustomerResponseDto = new AddressByCustomerResponseDto();
        BeanUtils.copyProperties(customer, addressByCustomerResponseDto);
        return addressByCustomerResponseDto;
    }
}
