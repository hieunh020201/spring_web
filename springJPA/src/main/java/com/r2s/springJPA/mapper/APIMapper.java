package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.APIResponseDto;
import com.r2s.springJPA.dto.response.CategoryResponseDto;
import com.r2s.springJPA.entity.API;
import com.r2s.springJPA.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class APIMapper {
    public List<APIResponseDto> convertEntitiesResponseDtos(List<API> apis) {
        return apis.stream().map(this::convertEntityResponseDto).toList();
    }

    public APIResponseDto convertEntityResponseDto(API api) {
        APIResponseDto responseDto = new APIResponseDto();
        responseDto.setMethod(api.getMethod());
        responseDto.setPath(api.getPath());
        Set<String> set = new HashSet<>();
        api.getRoles().stream().forEach(item -> {
            set.add(item.getAuthority());
        });
        responseDto.setRoles(set);
        return responseDto;
    }
}
