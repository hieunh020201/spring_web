package com.r2s.springJPA.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateOrderRequestDTO implements Serializable {

    private int cartId;

    private int totalCost;

    private String status;
}
