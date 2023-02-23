package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.CartLineItemResponseDto;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.CartLineItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartLineItemMapper {
    public List<CartLineItemResponseDto> convertEntitiesResponseDtos(List<CartLineItem> carts) {
        return carts.stream().map(this::convertEntityResponseDto).toList();
    }

    public CartLineItemResponseDto convertEntityResponseDto(CartLineItem cart) {
        CartLineItemResponseDto cartResponseDto = new CartLineItemResponseDto();
        BeanUtils.copyProperties(cart, cartResponseDto);
        return cartResponseDto;
    }
}
