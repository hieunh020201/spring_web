package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {
    public List<ProductResponseDto> convertEntitiesResponseDtos(List<Product> products) {
        return products.stream().map(this::convertEntityResponseDto).toList();
    }

    public ProductResponseDto convertEntityResponseDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        BeanUtils.copyProperties(product, productResponseDto);
        return productResponseDto;
    }
}
