package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
}
