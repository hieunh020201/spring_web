package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CartRequestDTO;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.mapper.CartMapper;
import com.r2s.springJPA.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public PageResponseDto getAllCarts(Pageable pageable) {
        Page<Cart> cartsPage = cartRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Carts by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(cartsPage.getNumber());
        pageResponseDto.setSize(cartsPage.getSize());
        pageResponseDto.setTotalPages(cartsPage.getTotalPages());
        pageResponseDto.setTotalRecord(cartsPage.getTotalElements());
        List<CartResponseDto> cartResponseDtos = cartMapper.convertEntitiesResponseDtos(cartsPage.getContent());
        pageResponseDto.setData(cartResponseDtos);
        return pageResponseDto;
    }

    @Override
    public CartResponseDto insertCart(CartRequestDTO requestDTO) {
        try {
            Cart cart = new Cart();
            cart.setCustomerId(requestDTO.getCustomerId());
            cart.setDeleted(false);
            return cartMapper.convertEntityResponseDto(cartRepository.save(cart));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public CartResponseDto getCartByCartId(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));
        return cartMapper.convertEntityResponseDto(cart);
    }

    @Override
    public void deleteCartByCartId(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));
        cart.setDeleted(true);
        cartMapper.convertEntityResponseDto(cartRepository.save(cart));
    }
}
