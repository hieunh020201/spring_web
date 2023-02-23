package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.AddressResponseDto;
import com.r2s.springJPA.entity.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressMapper {
    public List<AddressResponseDto> convertEntitiesResponseDtos(List<Address> addresses) {
        return addresses.stream().map(this::convertEntityResponseDto).toList();
    }
    public AddressResponseDto convertEntityResponseDto(Address address) {
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        BeanUtils.copyProperties(address, addressResponseDto);
        return addressResponseDto;
    }
}
