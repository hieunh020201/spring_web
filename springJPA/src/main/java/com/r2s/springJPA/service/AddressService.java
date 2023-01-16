package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateAddressByCustomerRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.AddressResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public interface AddressService {
    public PageResponseDto getAllAddresses(Pageable pageable);

    public AddressResponseDto insertAddress(CreateAddressRequestDto requestDto);

    public AddressResponseDto updateAddress(int addressId, UpdateAddressRequestDto requestDto);

    public AddressResponseDto getAddressById(int addressId);

    public void deleteAddress(int addressId);

    public AddressResponseDto insertAddressByCustomer(int customerId, CreateAddressByCustomerRequestDTO requestDTO);

    public AddressResponseDto getAddressByCustomer(int customerId);

    public AddressResponseDto updateAddressByCustomer(int customerId, UpdateAddressRequestDto requestDto);

    public void deleteAddressByCustomer(int customerId);
}
