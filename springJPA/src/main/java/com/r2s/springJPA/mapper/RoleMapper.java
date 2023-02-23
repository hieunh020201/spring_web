package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.dto.response.RoleResponseDto;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleMapper {
    public List<RoleResponseDto> convertEntitiesResponseDtos(List<Role> roles) {
        return roles.stream().map(this::convertEntityResponseDto).toList();
    }

    public RoleResponseDto convertEntityResponseDto(Role role) {
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        BeanUtils.copyProperties(role, roleResponseDto);
        return roleResponseDto;
    }
}
