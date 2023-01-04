package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UsersService {
    public List<Users> getAllUsers();

    public Users insertUser(CreateUserRequestDTO requestDTO);
}
