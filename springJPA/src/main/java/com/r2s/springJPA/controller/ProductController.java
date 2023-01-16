package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.CreateProductRequestDTO;
import com.r2s.springJPA.dto.request.UpdateProductRequestDto;
import com.r2s.springJPA.dto.response.AddressResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> getALlProducts(Pageable pageable) {
        PageResponseDto pageResponseDto = productService.getAllProducts(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertProduct(@RequestBody CreateProductRequestDTO requestDTO) {
        ProductResponseDto productResponseDto = productService.insertProduct(requestDTO);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{product-id}")
    public ResponseEntity<?> updateProduct(@PathVariable("product-id") int productId, @RequestBody UpdateProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.updateProduct(productId, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<?> getProductByProductId(@PathVariable("product-id") int productId) {
        ProductResponseDto responseDto = productService.getProductByProductId(productId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<?> deletedProductByProductId(@PathVariable("product-id") int productId) {
        productService.deleteProduct(productId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Product Id: ").append(productId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
