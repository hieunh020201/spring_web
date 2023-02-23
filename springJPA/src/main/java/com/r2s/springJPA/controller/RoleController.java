package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.RoleRequestDto;
import com.r2s.springJPA.dto.response.RoleResponseDto;
import com.r2s.springJPA.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<?> insertRole(@RequestBody RoleRequestDto requestDTO) {
        RoleResponseDto roleResponseDto = roleService.insertRole(requestDTO);
        return new ResponseEntity<>(roleResponseDto, HttpStatus.OK);
    }
}
