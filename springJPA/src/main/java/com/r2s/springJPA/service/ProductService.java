package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateProductRequestDTO;
import com.r2s.springJPA.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    public List<Product> getAllProducts();

    public Product insertProduct(CreateProductRequestDTO requestDTO);
}
