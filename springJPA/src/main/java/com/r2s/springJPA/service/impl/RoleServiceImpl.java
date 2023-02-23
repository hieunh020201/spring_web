package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.dto.request.RoleRequestDto;
import com.r2s.springJPA.dto.response.RoleResponseDto;
import com.r2s.springJPA.entity.Role;
import com.r2s.springJPA.mapper.RoleMapper;
import com.r2s.springJPA.repository.RoleRepository;
import com.r2s.springJPA.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleResponseDto insertRole(RoleRequestDto requestDto) {
        roleRepository.findAll().forEach(item -> {
            if (item.getAuthority().equals(requestDto.getAuthority())) {
                throw new IllegalArgumentException("Role already exists");
            }
        });
        Role role = new Role();
        role.setAuthority(requestDto.getAuthority());

        return roleMapper.convertEntityResponseDto(roleRepository.save(role));
    }
}
