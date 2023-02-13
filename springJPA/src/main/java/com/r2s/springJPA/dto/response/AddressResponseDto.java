package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressResponseDto implements Serializable {
    private String province;

    private String district;

    private String street;
}
