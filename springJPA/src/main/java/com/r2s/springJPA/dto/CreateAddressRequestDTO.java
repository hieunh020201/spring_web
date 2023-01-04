package com.r2s.springJPA.dto;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
public class CreateAddressRequestDTO implements Serializable {
    private String name;

    private String phone;

    private String province;

    private String district;

    private String street;

    private boolean type;

    private boolean defaultAddress;

    private int userId;
}
