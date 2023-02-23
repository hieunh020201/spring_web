package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.AddressByUserResponseDto;
import com.r2s.springJPA.dto.response.UserResponseDto;
import com.r2s.springJPA.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public List<UserResponseDto> convertEntitiesResponseDtos(List<User> users) {
        return users.stream().map(this::convertEntityResponseDto).toList();
    }

    public UserResponseDto convertEntityResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(user, userResponseDto);
        return userResponseDto;
    }

    public AddressByUserResponseDto convertEntityResponseDtoV2(User user) {
        AddressByUserResponseDto addressByUserResponseDto = new AddressByUserResponseDto();
        BeanUtils.copyProperties(user, addressByUserResponseDto);
        return addressByUserResponseDto;
    }
}
