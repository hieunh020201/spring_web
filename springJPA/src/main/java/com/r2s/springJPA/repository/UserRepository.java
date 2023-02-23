package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<Page<User>> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);
}
