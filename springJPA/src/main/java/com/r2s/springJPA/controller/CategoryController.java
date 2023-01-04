package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.CreateCategoryRequestDTO;
import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Category;
import com.r2s.springJPA.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> getALlCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCategory(@RequestBody CreateCategoryRequestDTO requestDTO) {
        Category category = categoryService.insertCategory(requestDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
