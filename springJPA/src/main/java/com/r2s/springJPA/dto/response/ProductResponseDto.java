package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResponseDto implements Serializable {
    private String name;

    private int price;
}
