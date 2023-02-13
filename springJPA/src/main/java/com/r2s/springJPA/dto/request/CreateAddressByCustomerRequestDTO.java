package com.r2s.springJPA.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class CreateAddressByCustomerRequestDTO implements Serializable {

    private int addressId;

    private String name;

    private String phone;

    private boolean type;

    private boolean defaultAddress;
}
