package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.CategoryResponseDto;
import com.r2s.springJPA.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {
    public List<CategoryResponseDto> convertEntitiesResponseDtos(List<Category> categories) {
        return categories.stream().map(this::convertEntityResponseDto).toList();
    }

    public CategoryResponseDto convertEntityResponseDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        BeanUtils.copyProperties(category, categoryResponseDto);
        return categoryResponseDto;
    }
}
