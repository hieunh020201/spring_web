package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateCategoryRequestDTO;
import com.r2s.springJPA.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryService {
    public List<Category> getAllCategories();

    public Category insertCategory(CreateCategoryRequestDTO requestDTO);
}
