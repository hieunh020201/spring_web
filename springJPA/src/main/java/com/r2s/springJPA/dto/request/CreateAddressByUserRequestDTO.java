package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateAddressByUserRequestDTO implements Serializable {

    private Integer addressId;

    private String name;

    private String phone;

    private boolean type;

    private boolean defaultAddress;
}
