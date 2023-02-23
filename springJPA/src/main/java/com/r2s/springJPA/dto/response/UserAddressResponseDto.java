package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAddressResponseDto implements Serializable {
    private String name;

    private String phone;

    private boolean type;

    private boolean defaultAddress;

    private AddressResponseDto addressResponseDto;
}
