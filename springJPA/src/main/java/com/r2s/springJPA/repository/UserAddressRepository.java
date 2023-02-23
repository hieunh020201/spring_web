package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.UserAddress;
import com.r2s.springJPA.entity.UserAddressId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress, UserAddressId> {

//    @Query("SELECT c FROM UserAddress c WHERE c.user.id = ?1 " +
//            "AND c.address.isDeleted = false")
//    Optional<List<UserAddress>> findAllByUserId(int userId);
}
