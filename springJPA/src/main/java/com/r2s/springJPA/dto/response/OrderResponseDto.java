package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderResponseDto implements Serializable {
    private int cartId;

    private int totalCost;

    private String status;
}
