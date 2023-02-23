package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.config.JwtTokenUtil;
import com.r2s.springJPA.dto.request.CreateUserRequestDTO;
import com.r2s.springJPA.dto.request.LoginRequest;
import com.r2s.springJPA.dto.request.RegisterUserRequestDto;
import com.r2s.springJPA.dto.request.UpdateUserRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.UserResponseDto;
import com.r2s.springJPA.entity.Role;
import com.r2s.springJPA.entity.User;
import com.r2s.springJPA.mapper.UserMapper;
import com.r2s.springJPA.repository.RoleRepository;
import com.r2s.springJPA.repository.UserRepository;
import com.r2s.springJPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageResponseDto getAllUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Users by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(usersPage.getNumber());
        pageResponseDto.setSize(usersPage.getSize());
        pageResponseDto.setTotalPages(usersPage.getTotalPages());
        pageResponseDto.setTotalRecord(usersPage.getTotalElements());
        List<UserResponseDto> userResponseDtos = userMapper.convertEntitiesResponseDtos(usersPage.getContent());
        pageResponseDto.setData(userResponseDtos);
        return pageResponseDto;
    }

    @Override
    @Transactional
    public UserResponseDto insertUser(CreateUserRequestDTO requestDTO) {
        User user = new User();
        user.setName(requestDTO.getName());
        user.setUsername(requestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setPhone(requestDTO.getPhone());
        user.setEmail(requestDTO.getEmail());
        user.setGender(requestDTO.getGender());
        user.setBirthday(requestDTO.getBirthday());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByAuthority(requestDTO.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role doesn't exist"));
        roles.add(role);
        user.setRoles(roles);
        user.setDeleted(false);
        return userMapper.convertEntityResponseDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(int userId, UpdateUserRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
        user.setName(requestDto.getName());
        user.setPhone(requestDto.getPhone());
        user.setEmail(requestDto.getEmail());
        user.setBirthday(requestDto.getBirthday());
        user.setGender(requestDto.getGender());
        return userMapper.convertEntityResponseDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public UserResponseDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
        return userMapper.convertEntityResponseDto(user);
    }

    @Override
    public String login(LoginRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).get();

        if(ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("User Not Found Exception");
        } else {
            if(!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())){
                throw new AuthenticationServiceException("Wrong password");
            }
        }
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public UserResponseDto register(RegisterUserRequestDto requestDTO) {
        User user = new User();
        user.setName(requestDTO.getName());
        user.setUsername(requestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setPhone(requestDTO.getPhone());
        user.setEmail(requestDTO.getEmail());
        user.setGender(requestDTO.getGender());
        user.setBirthday(requestDTO.getBirthday());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByAuthority("USER")
                .orElseThrow(() -> new IllegalArgumentException("Role doesn't exist"));
        roles.add(role);
        user.setRoles(roles);
        user.setDeleted(false);
        return userMapper.convertEntityResponseDto(userRepository.save(user));
    }


}
