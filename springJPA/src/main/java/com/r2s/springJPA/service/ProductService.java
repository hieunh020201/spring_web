package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.ProductRequestDTO;
import com.r2s.springJPA.dto.request.UpdateProductRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    PageResponseDto getAllProducts(Pageable pageable);

    ProductResponseDto getProductByProductId(int productId);

    void deleteProduct(int productId);

    PageResponseDto getAllProductsByCategory(int categoryId, Pageable pageable);

    ProductResponseDto insertProductByCategory(int categoryId, ProductRequestDTO requestDTO);

    ProductResponseDto updateProductByCategory(int categoryId, int productId, ProductRequestDTO requestDTO);

    ProductResponseDto getProductByCategory(int categoryId, int productId);

    void deleteProductByCategory(int categoryId, int productId);
}
