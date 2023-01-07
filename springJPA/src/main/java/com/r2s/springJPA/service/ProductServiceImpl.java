package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateProductRequestDTO;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product insertProduct(CreateProductRequestDTO requestDTO) {
        try {
            Product product = new Product();
            product.setName(requestDTO.getName());
            product.setPrice(requestDTO.getPrice());
            product.setCategoryId(requestDTO.getCategoryId());
            product.setDeleted(false);
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
