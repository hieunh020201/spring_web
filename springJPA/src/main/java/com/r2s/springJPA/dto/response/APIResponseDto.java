package com.r2s.springJPA.dto.response;

import com.r2s.springJPA.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class APIResponseDto implements Serializable {

    private String path;

    private Set<String> roles;

    private String method;
}
