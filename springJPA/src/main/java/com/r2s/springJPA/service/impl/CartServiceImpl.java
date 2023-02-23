package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.dto.request.CartRequestDTO;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.User;
import com.r2s.springJPA.mapper.CartMapper;
import com.r2s.springJPA.repository.CartRepository;
import com.r2s.springJPA.repository.UserRepository;
import com.r2s.springJPA.service.CartService;
import com.r2s.springJPA.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public PageResponseDto getAllCartsByUser(int userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserId is invalid"));
        Page<Cart> cartsPage = cartRepository.findAllByUser(user, pageable)
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
    public CartResponseDto getCartByUser(int userId, int cartId) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));
        return cartMapper.convertEntityResponseDto(cart);
    }

    @Override
    public void deleteCartByUser(int userId, int cartId) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));

        cart.setDeleted(true);
        cartRepository.save(cart);
    }

    @Override
    public CartResponseDto insertCartByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserId is invalid"));
        if (user.getCarts().stream().filter(item -> item.getStatus().equals(Constants.BUYING))
                .findAny().isPresent()) {
            throw new RuntimeException("Can't insert cart");
        }
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setStatus(Constants.BUYING);
        cart.setDeleted(false);
        return cartMapper.convertEntityResponseDto(cartRepository.save(cart));
    }

    @Override
    public CartResponseDto updateCartByUser(int userId, int cartId) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));

        cart.setStatus(Constants.ORDERED);

        return cartMapper.convertEntityResponseDto(cartRepository.save(cart));
    }

}
