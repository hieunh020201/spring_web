package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartRequestDTO implements Serializable {
    private int customerId;
}
