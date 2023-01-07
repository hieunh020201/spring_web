package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateCategoryRequestDTO;
import com.r2s.springJPA.entity.Category;
import com.r2s.springJPA.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category insertCategory(CreateCategoryRequestDTO requestDTO) {
        try {
            Category category = new Category();
            category.setName(requestDTO.getName());
            return categoryRepository.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
