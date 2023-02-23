package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.API;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface APIRepository extends CrudRepository<API, Integer> {
    Optional<Page<API>> findAll(Pageable pageable);
}
