package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCartLineItemRequestDto implements Serializable {
    private String name;

    private int quantity;

    private int cost;
}
