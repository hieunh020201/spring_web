package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleRequestDto implements Serializable {
    private String authority;
}
