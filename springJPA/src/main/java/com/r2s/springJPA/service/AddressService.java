package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateAddressByUserRequestDTO;
import com.r2s.springJPA.dto.request.CreateAddressRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressByUserRequestDto;
import com.r2s.springJPA.dto.request.UpdateAddressRequestDto;
import com.r2s.springJPA.dto.response.*;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    public PageResponseDto getAllAddresses(Pageable pageable);

    public AddressResponseDto insertAddress(CreateAddressRequestDto requestDto);

    public AddressResponseDto updateAddress(int addressId, UpdateAddressRequestDto requestDto);

    public AddressResponseDto getAddressById(int addressId);

    public void deleteAddress(int addressId);

    public UserAddressResponseDto insertAddressByUser(int userId, CreateAddressByUserRequestDTO requestDTO);

    public AddressByUserResponseDto getListAddressesByUser(int userId);

    UserAddressResponseDto getAddressByUser(int userId, int addressId);

    UserAddressResponseDto updateAddressByUser(int userId, int addressId, UpdateAddressByUserRequestDto requestDto);

    public void deleteAddressByUser(int userId, int addressId);
}
