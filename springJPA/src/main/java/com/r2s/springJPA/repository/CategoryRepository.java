package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Optional<Page<Category>> findAll(Pageable pageable);
}
