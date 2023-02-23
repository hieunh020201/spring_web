package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateUserRequestDTO;
import com.r2s.springJPA.dto.request.LoginRequest;
import com.r2s.springJPA.dto.request.RegisterUserRequestDto;
import com.r2s.springJPA.dto.request.UpdateUserRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.UserResponseDto;
import com.r2s.springJPA.entity.User;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public PageResponseDto getAllUsers(Pageable pageable);

    public UserResponseDto insertUser(CreateUserRequestDTO requestDTO);

    public UserResponseDto updateUser(int userId, UpdateUserRequestDto requestDto);

    public void deleteUser(int userId);

    public UserResponseDto getUserById(int userId);

    String login(LoginRequest authenticationRequest);

    UserResponseDto register(RegisterUserRequestDto registerRequest);

}
