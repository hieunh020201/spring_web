package com.r2s.springJPA.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleResponseDto implements Serializable {
    private String authority;
}
