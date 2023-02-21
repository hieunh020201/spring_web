package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CategoryRequestDTO;
import com.r2s.springJPA.dto.response.CategoryResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Category;
import com.r2s.springJPA.mapper.CategoryMapper;
import com.r2s.springJPA.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResponseDto getAllCategories(Pageable pageable) {
        Page<Category> categoriesPage = categoryRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Categories by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(categoriesPage.getNumber());
        pageResponseDto.setSize(categoriesPage.getSize());
        pageResponseDto.setTotalPages(categoriesPage.getTotalPages());
        pageResponseDto.setTotalRecord(categoriesPage.getTotalElements());
        List<CategoryResponseDto> categoryResponseDtos = categoryMapper.convertEntitiesResponseDtos(categoriesPage.getContent());
        pageResponseDto.setData(categoryResponseDtos);
        return pageResponseDto;
    }

    @Override
    public CategoryResponseDto insertCategory(CategoryRequestDTO requestDTO) {
        try {
            Category category = new Category();
            category.setName(requestDTO.getName());
            return categoryMapper.convertEntityResponseDto(categoryRepository.save(category));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CategoryResponseDto updateCategory(int categoryId, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category doesn't exist"));

        category.setName(requestDTO.getName());

        return categoryMapper.convertEntityResponseDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto getCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category doesn't exist"));

        return categoryMapper.convertEntityResponseDto(category);
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category doesn't exist"));

        category.setDeleted(true);

        categoryMapper.convertEntityResponseDto(categoryRepository.save(category));
    }


}
