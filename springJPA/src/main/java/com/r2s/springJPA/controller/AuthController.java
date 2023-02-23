package com.r2s.springJPA.controller;

import com.r2s.springJPA.config.JwtTokenUtil;
import com.r2s.springJPA.dto.request.CreateUserRequestDTO;
import com.r2s.springJPA.dto.request.LoginRequest;
import com.r2s.springJPA.dto.request.RegisterUserRequestDto;
import com.r2s.springJPA.dto.response.JwtResponse;
import com.r2s.springJPA.dto.response.UserResponseDto;
import com.r2s.springJPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) {

        String token = userService.login(authenticationRequest);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequestDto registerRequest){
        UserResponseDto user = userService.register(registerRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
