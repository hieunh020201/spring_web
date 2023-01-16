package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    public List<CartResponseDto> convertEntitiesResponseDtos(List<Cart> carts) {
        return carts.stream().map(this::convertEntityResponseDto).toList();
    }

    public CartResponseDto convertEntityResponseDto(Cart cart) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        BeanUtils.copyProperties(cart, cartResponseDto);
        return cartResponseDto;
    }
}
