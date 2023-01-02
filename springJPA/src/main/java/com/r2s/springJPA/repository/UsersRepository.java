package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Integer> {
}
