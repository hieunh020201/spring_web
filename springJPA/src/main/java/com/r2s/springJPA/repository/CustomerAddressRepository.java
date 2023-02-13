package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.CustomerAddress;
import com.r2s.springJPA.entity.CustomerAddressId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends CrudRepository<CustomerAddress, CustomerAddressId> {

    @Query("SELECT c FROM CustomerAddress c WHERE c.customerAddressId.customer.id = ?1 " +
            "AND c.customerAddressId.address.isDeleted = false")
    Optional<List<CustomerAddress>> findAllByCustomerId(int CustomerId);
}
