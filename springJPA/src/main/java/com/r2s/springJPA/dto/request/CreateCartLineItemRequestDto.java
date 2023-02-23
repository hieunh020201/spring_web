package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCartLineItemRequestDto implements Serializable {
    private int productId;

    private String name;

    private int quantity;

    private int cost;
}
