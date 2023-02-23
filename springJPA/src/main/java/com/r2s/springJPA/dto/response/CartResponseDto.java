package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartResponseDto implements Serializable {
    private int id;

    private String status;

    private List<CartLineItemResponseDto> cartResponseDtos;
}
