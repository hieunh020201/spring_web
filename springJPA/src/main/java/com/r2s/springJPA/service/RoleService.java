package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.RoleRequestDto;
import com.r2s.springJPA.dto.response.RoleResponseDto;

public interface RoleService {
    RoleResponseDto insertRole(RoleRequestDto requestDto);

}
