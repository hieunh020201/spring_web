package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateAddressByCustomerRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressByCustomerRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AddressService {
    public PageResponseDto getAllAddresses(Pageable pageable);

    public AddressResponseDto insertAddress(CreateAddressRequestDto requestDto);

    public AddressResponseDto updateAddress(int addressId, UpdateAddressRequestDto requestDto);

    public AddressResponseDto getAddressById(int addressId);

    public void deleteAddress(int addressId);

    public CustomerAddressResponseDto insertAddressByCustomer(int customerId, CreateAddressByCustomerRequestDTO requestDTO);

    public AddressByCustomerResponseDto getListAddressesByCustomer(int customerId);

    CustomerAddressResponseDto updateAddressByCustomer(int customerId, int addressId, UpdateAddressByCustomerRequestDto requestDto);

    public void deleteAddressByCustomer(int customerId);
}
