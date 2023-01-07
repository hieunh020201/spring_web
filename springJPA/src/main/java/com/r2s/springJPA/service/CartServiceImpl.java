package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateCartRequestDTO;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    @Override
    public Cart insertCart(CreateCartRequestDTO requestDTO) {
        try {
            Cart cart = new Cart();
            cart.setUserId(requestDTO.getUserId());
            cart.setDeleted(false);
            return cartRepository.save(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
