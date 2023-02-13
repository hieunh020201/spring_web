package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CartRequestDTO;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public interface CartService {
    public PageResponseDto getAllCarts(Pageable pageable);

    public CartResponseDto getCartByCartId(int cartId);

    public void deleteCartByCartId(int cartId);

    CartResponseDto insertCartByCustomer(int customerId);
}
