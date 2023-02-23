package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CartRequestDTO;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public interface CartService {
    PageResponseDto getAllCartsByUser(int userId, Pageable pageable);

    CartResponseDto getCartByUser(int userId, int cartId);

    void deleteCartByUser(int userId, int cartId);

    CartResponseDto insertCartByUser(int userId);

    CartResponseDto updateCartByUser(int userId, int cartId);
}
