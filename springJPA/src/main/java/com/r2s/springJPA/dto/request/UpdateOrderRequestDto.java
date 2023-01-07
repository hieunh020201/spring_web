package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateOrderRequestDto implements Serializable {
    private int totalCost;

    private String status;
}
