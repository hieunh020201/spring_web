package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateAddressRequestDto implements Serializable {
    private String province;

    private String district;

    private String street;
}
