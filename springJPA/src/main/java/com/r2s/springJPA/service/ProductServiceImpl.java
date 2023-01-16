package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateProductRequestDTO;
import com.r2s.springJPA.dto.request.UpdateProductRequestDto;
import com.r2s.springJPA.dto.response.CategoryResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Category;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.mapper.ProductMapper;
import com.r2s.springJPA.repository.CategoryRepository;
import com.r2s.springJPA.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

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
    @Transactional
    public ProductResponseDto insertProduct(CreateProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("CategoryID is invalid")));
        product.setDeleted(false);
        return productMapper.convertEntityResponseDto(productRepository.save(product));
    }

    @Override
    public ProductResponseDto updateProduct(int productId, UpdateProductRequestDto requestDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
        product.setName(requestDto.getName());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("CategoryID is invalid")));

        return productMapper.convertEntityResponseDto(productRepository.save(product));
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


}
