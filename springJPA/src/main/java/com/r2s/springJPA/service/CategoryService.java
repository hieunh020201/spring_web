package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CategoryRequestDTO;
import com.r2s.springJPA.dto.response.CategoryResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public interface CategoryService {
    public PageResponseDto getAllCategories(Pageable pageable);

    public CategoryResponseDto insertCategory(CategoryRequestDTO requestDTO);

    public CategoryResponseDto updateCategory(int categoryId, CategoryRequestDTO requestDTO);

    public CategoryResponseDto getCategoryById(int categoryId);

    public void deleteCategoryById(int categoryId);
}
