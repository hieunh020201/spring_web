package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Orders;
import com.r2s.springJPA.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {
    Optional<Page<Orders>> findAll(Pageable pageable);
}
