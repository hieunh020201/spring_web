package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.CreateProductRequestDTO;
import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.entity.Users;
import com.r2s.springJPA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getALlProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        Product product = productService.insertProduct(requestDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
