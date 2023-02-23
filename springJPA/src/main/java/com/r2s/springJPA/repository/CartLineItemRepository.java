package com.r2s.springJPA.repository;

import com.r2s.springJPA.entity.CartLineItem;
import com.r2s.springJPA.entity.CartLineItemId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartLineItemRepository extends CrudRepository<CartLineItem, CartLineItemId> {
    @Query("SELECT c FROM CartLineItem c WHERE c.cartLineItemId.cart.id = ?1 ")
    Optional<List<CartLineItem>> findAllCartLineItemByCart(int cartId);
}
