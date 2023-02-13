package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductRequestDTO implements Serializable {
    private String name;

    private int price;
}
