package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Optional<Page<Cart>> findAll(Pageable pageable);

    Optional<Page<Cart>> findAllByUser(User user, Pageable pageable);

    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1 " +
            "AND c.id = ?2")
    Optional<Cart> findCartByUser(int userId, int cartId);
}
