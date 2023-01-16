package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.AddressByCustomerResponseDto;
import com.r2s.springJPA.dto.response.CustomerAddressResponseDto;
import com.r2s.springJPA.dto.response.CustomerResponseDto;
import com.r2s.springJPA.entity.Customer;
import com.r2s.springJPA.entity.CustomerAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerAddressMapper {

    @Autowired
    private AddressMapper addressMapper;

    public List<CustomerAddressResponseDto> convertEntitiesResponseDtos(List<CustomerAddress> customerAddresses) {
        List<CustomerAddressResponseDto> customerAddressResponseDtos = new ArrayList<>();
        customerAddresses.stream().forEach(customerAddress -> {
            CustomerAddressResponseDto customerAddressResponseDto = this.convertEntityResponseDto(customerAddress);
            customerAddressResponseDto.setAddress(addressMapper
                    .convertEntityResponseDto(customerAddress.getCustomerAddressId().getAddress()));
            customerAddressResponseDtos.add(customerAddressResponseDto);
        });
        return customerAddressResponseDtos;
    }

    public CustomerAddressResponseDto convertEntityResponseDto(CustomerAddress customerAddress) {
        CustomerAddressResponseDto customerAddressResponseDto = new CustomerAddressResponseDto();
        BeanUtils.copyProperties(customerAddress, customerAddressResponseDto);
        return customerAddressResponseDto;
    }

}
