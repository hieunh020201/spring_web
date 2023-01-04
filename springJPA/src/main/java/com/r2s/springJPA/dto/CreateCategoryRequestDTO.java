package com.r2s.springJPA.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCategoryRequestDTO implements Serializable {

    private String name;
}
