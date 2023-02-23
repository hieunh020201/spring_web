package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.UserAddressResponseDto;
import com.r2s.springJPA.entity.UserAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAddressMapper {

    @Autowired
    private AddressMapper addressMapper;

    public List<UserAddressResponseDto> convertEntitiesResponseDtos(List<UserAddress> userAddresses) {
        List<UserAddressResponseDto> userAddressResponseDtos = new ArrayList<>();
        userAddresses.stream().forEach(customerAddress -> {
            UserAddressResponseDto userAddressResponseDto = this.convertEntityResponseDto(customerAddress);
            userAddressResponseDto.setAddressResponseDto(addressMapper
                    .convertEntityResponseDto(customerAddress.getUserAddressId().getAddress()));
            userAddressResponseDtos.add(userAddressResponseDto);
        });
        return userAddressResponseDtos;
    }

    public UserAddressResponseDto convertEntityResponseDto(UserAddress userAddress) {
        UserAddressResponseDto userAddressResponseDto = new UserAddressResponseDto();
        BeanUtils.copyProperties(userAddress, userAddressResponseDto);
        return userAddressResponseDto;
    }

}
