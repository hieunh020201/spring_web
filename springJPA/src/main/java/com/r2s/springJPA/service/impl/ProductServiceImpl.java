package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.dto.request.ProductRequestDTO;
import com.r2s.springJPA.dto.request.UpdateProductRequestDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Category;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.mapper.ProductMapper;
import com.r2s.springJPA.repository.CategoryRepository;
import com.r2s.springJPA.repository.ProductRepository;
import com.r2s.springJPA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResponseDto getAllProducts(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Products by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(productsPage.getNumber());
        pageResponseDto.setSize(productsPage.getSize());
        pageResponseDto.setTotalPages(productsPage.getTotalPages());
        pageResponseDto.setTotalRecord(productsPage.getTotalElements());
        List<ProductResponseDto> productResponseDtos = productMapper.convertEntitiesResponseDtos(productsPage.getContent());
        pageResponseDto.setData(productResponseDtos);
        return pageResponseDto;
    }

    @Override
    public ProductResponseDto getProductByProductId(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
        return productMapper.convertEntityResponseDto(product);
    }

    @Override
    public void deleteProduct(int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
        product.setDeleted(true);
        productRepository.save(product);
    }

    @Override
    public PageResponseDto getAllProductsByCategory(int categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("CategoryId is invalid"));

        Page<Product> productsPage = productRepository.findAllByCategory(category, pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Products by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(productsPage.getNumber());
        pageResponseDto.setSize(productsPage.getSize());
        pageResponseDto.setTotalPages(productsPage.getTotalPages());
        pageResponseDto.setTotalRecord(productsPage.getTotalElements());
        List<ProductResponseDto> productResponseDtos = productMapper.convertEntitiesResponseDtos(productsPage.getContent());
        pageResponseDto.setData(productResponseDtos);
        return pageResponseDto;
    }

    @Override
    public ProductResponseDto insertProductByCategory(int categoryId, ProductRequestDTO requestDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("CategoryId is invalid"));

        Product product = new Product();
        product.setCategory(category);
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setDeleted(false);

        return productMapper.convertEntityResponseDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto updateProductByCategory(int categoryId, int productId, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("ProductId is invalid"));
        if (product.getCategory().getId() != categoryId) {
            throw new IllegalArgumentException("CategoryId is invalid");
        }
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());

        return productMapper.convertEntityResponseDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto getProductByCategory(int categoryId, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("ProductId is invalid"));
        if (product.getCategory().getId() != categoryId) {
            throw new IllegalArgumentException("CategoryId is invalid");
        }
        return productMapper.convertEntityResponseDto(product);
    }

    @Override
    public void deleteProductByCategory(int categoryId, int productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("ProductId is invalid"));
        if (product.getCategory().getId() != categoryId) {
            throw new IllegalArgumentException("CategoryId is invalid");
        }
        product.setDeleted(true);
        productRepository.save(product);
    }


}
