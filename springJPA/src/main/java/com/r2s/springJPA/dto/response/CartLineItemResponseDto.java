package com.r2s.springJPA.dto.response;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class CartLineItemResponseDto implements Serializable {
    private String name;

    private int quantity;

    private int cost;
}
