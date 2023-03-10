package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Category;
import com.r2s.springJPA.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<Page<Product>> findAll(Pageable pageable);

    Optional<Page<Product>> findAllByCategory(Category category, Pageable pageable);
}
