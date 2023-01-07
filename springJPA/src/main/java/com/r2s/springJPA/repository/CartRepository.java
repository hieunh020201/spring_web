package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Page<Cart>> findAll(Pageable pageable);
}
