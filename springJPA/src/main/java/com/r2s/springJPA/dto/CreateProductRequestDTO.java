package com.r2s.springJPA.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateProductRequestDTO implements Serializable {
    private String name;

    private int price;

    private int categoryId;

}
