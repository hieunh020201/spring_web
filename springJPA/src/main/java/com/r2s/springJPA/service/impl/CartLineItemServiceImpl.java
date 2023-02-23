package com.r2s.springJPA.service.impl;

import com.r2s.springJPA.dto.request.CreateCartLineItemRequestDto;
import com.r2s.springJPA.dto.request.UpdateCartLineItemRequestDto;
import com.r2s.springJPA.dto.response.CartLineItemResponseDto;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.CartLineItem;
import com.r2s.springJPA.entity.CartLineItemId;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.mapper.CartLineItemMapper;
import com.r2s.springJPA.mapper.CartMapper;
import com.r2s.springJPA.repository.CartLineItemRepository;
import com.r2s.springJPA.repository.CartRepository;
import com.r2s.springJPA.repository.ProductRepository;
import com.r2s.springJPA.service.CartLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartLineItemServiceImpl implements CartLineItemService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartLineItemRepository cartLineItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartLineItemMapper cartLineItemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartResponseDto getDetailsCartByUser(int userId, int cartId) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));
        List<CartLineItemResponseDto> cartLineItemResponseDtos = cartLineItemMapper.convertEntitiesResponseDtos(cart.getCartLineItems());
        CartResponseDto cartResponseDto = cartMapper.convertEntityResponseDto(cart);
        cartResponseDto.setCartResponseDtos(cartLineItemResponseDtos);
        return cartResponseDto;
    }

    @Override
    public CartLineItemResponseDto getDetailCartByUser(int userId, int cartId, int productId) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));
        CartLineItem cartLineItem = cart.getCartLineItems().stream().filter(item -> item.getCartLineItemId().getProduct().getId() == productId)
                .findAny().orElseThrow(() -> new IllegalArgumentException("Cart hasn't this product"));
        return cartLineItemMapper.convertEntityResponseDto(cartLineItem);
    }

    @Override
    public CartResponseDto insertCartLineItemByUser(int userId, int cartId, CreateCartLineItemRequestDto requestDto) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));

        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
        CartLineItem cartLineItem = new CartLineItem();
        cartLineItem.setCartLineItemId(new CartLineItemId(cart, product));
        cartLineItem.setName(requestDto.getName());
        cartLineItem.setQuantity(requestDto.getQuantity());
        cartLineItem.setCost(requestDto.getCost());
        cartLineItem.setDeleted(false);

        cartLineItemRepository.save(cartLineItem);

        return saveCartLineItem(cart);
    }

    @Override
    public CartResponseDto updateCartLineItemByUser(int userId, int cartId, int productId, UpdateCartLineItemRequestDto requestDto) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
        CartLineItem cartLineItem = cartLineItemRepository.findById(new CartLineItemId(cart, product))
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't have this product"));
        cartLineItem.setName(requestDto.getName());
        cartLineItem.setQuantity(requestDto.getQuantity());
        cartLineItem.setCost(requestDto.getCost());

        cartLineItemRepository.save(cartLineItem);

        return saveCartLineItem(cart);
    }

    @Override
    public void deleteCartLineItemByUser(int userId, int cartId, int productId) {
        Cart cart = cartRepository.findCartByUser(userId, cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't exist"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product doesn't exist"));
        CartLineItem cartLineItem = cartLineItemRepository.findById(new CartLineItemId(cart, product))
                .orElseThrow(() -> new IllegalArgumentException("Cart doesn't have this product"));
        cartLineItem.setDeleted(true);

        cartLineItemRepository.save(cartLineItem);
    }

    private CartResponseDto saveCartLineItem(Cart cart){
        List<CartLineItem> cartLineItems = cartLineItemRepository.findAllCartLineItemByCart(cart.getId())
                .orElseThrow(() -> new IllegalArgumentException("Error"));
        List<CartLineItemResponseDto> cartLineItemResponseDtos = cartLineItemMapper.convertEntitiesResponseDtos(cartLineItems);
        CartResponseDto cartResponseDto = cartMapper.convertEntityResponseDto(cart);
        cartResponseDto.setCartResponseDtos(cartLineItemResponseDtos);
        return cartResponseDto;
    }
}
