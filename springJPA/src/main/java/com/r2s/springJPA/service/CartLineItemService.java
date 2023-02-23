package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateCartLineItemRequestDto;
import com.r2s.springJPA.dto.request.UpdateCartLineItemRequestDto;
import com.r2s.springJPA.dto.response.CartLineItemResponseDto;
import com.r2s.springJPA.dto.response.CartResponseDto;

public interface CartLineItemService {
    CartResponseDto getDetailsCartByUser(int userId, int cartId);

    CartLineItemResponseDto getDetailCartByUser(int userId, int cartId, int productId);

    CartResponseDto insertCartLineItemByUser(int userId, int cartId, CreateCartLineItemRequestDto requestDto);

    CartResponseDto updateCartLineItemByUser(int userId, int cartId, int productId, UpdateCartLineItemRequestDto requestDto);

    void deleteCartLineItemByUser(int userId, int cartId, int productId);
}
