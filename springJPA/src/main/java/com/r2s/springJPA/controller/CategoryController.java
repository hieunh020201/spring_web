package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.CategoryRequestDTO;
import com.r2s.springJPA.dto.response.CategoryResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getALlCategories(Pageable pageable) {
        PageResponseDto pageResponseDto = categoryService.getAllCategories(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCategory(@RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDto categoryResponseDto = categoryService.insertCategory(requestDTO);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{category-id}")
    public ResponseEntity<?> updateCategory(@PathVariable("category-id") int categoryId,@RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, requestDTO);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{category-id}")
    public ResponseEntity<?> getCategoryByCategoryId(@PathVariable("category-id") int categoryId) {
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{category-id}")
    public ResponseEntity<?> deleteCategoryByCategoryId(@PathVariable("category-id") int categoryId) {
        categoryService.deleteCategoryById(categoryId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Category Id: ").append(categoryId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
