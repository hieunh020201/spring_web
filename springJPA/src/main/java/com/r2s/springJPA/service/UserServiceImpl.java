package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Users;
import com.r2s.springJPA.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<Users> getAllUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    @Override
    public Users insertUser(CreateUserRequestDTO requestDTO) {
        Users user = new Users();
        user.setName(requestDTO.getName());
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setPhone(requestDTO.getPhone());
        user.setEmail(requestDTO.getEmail());
        user.setGender(requestDTO.getGender());
        user.setBirthday(requestDTO.getBirthday());
        user.setRole(requestDTO.getRole());
        return usersRepository.save(user);
    }
}
