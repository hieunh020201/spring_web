package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateAddressRequestDTO;
import com.r2s.springJPA.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AddressService {
    public List<Address> getAllAddresses();

    public Address insertAddress(CreateAddressRequestDTO requestDTO);
}
