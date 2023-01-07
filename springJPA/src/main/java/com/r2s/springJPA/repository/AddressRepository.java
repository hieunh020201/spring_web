package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Address;
import com.r2s.springJPA.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
    Optional<Page<Address>> findAll(Pageable pageable);

    Optional<Address> findByCustomer(Customer customer);
}
