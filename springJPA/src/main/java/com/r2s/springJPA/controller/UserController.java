package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Users;
import com.r2s.springJPA.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<?> getALlUsers() {
        List<Users> users = usersService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody CreateUserRequestDTO requestDTO) {
        Users user = usersService.insertUser(requestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
