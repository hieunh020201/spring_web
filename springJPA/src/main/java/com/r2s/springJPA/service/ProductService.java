package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateProductRequestDTO;
import com.r2s.springJPA.dto.request.UpdateProductRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductService {

    public PageResponseDto getAllProducts(Pageable pageable);

    public ProductResponseDto insertProduct(CreateProductRequestDTO requestDTO);

    public ProductResponseDto updateProduct(int productId, UpdateProductRequestDto requestDto);

    public ProductResponseDto getProductByProductId(int productId);

    public void deleteProduct(int productId);
}
