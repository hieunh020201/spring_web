package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateCartRequestDTO;
import com.r2s.springJPA.entity.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    public List<Cart> getAllCarts();

    public Cart insertCart(CreateCartRequestDTO requestDTO);
}
