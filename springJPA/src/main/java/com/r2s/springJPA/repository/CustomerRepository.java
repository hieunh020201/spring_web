package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Optional<Page<Customer>> findAll(Pageable pageable);
}
