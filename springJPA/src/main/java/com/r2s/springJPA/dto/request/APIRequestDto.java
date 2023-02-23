package com.r2s.springJPA.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class APIRequestDto implements Serializable {
    private String path;

    private Set<String> roles;

    private String method;
}
