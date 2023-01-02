package com.r2s.springJPA.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCartRequestDTO implements Serializable {
    private int userId;
}
