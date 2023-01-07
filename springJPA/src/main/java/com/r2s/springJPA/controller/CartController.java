package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.CartRequestDTO;
import com.r2s.springJPA.dto.response.CartResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @GetMapping
    public ResponseEntity<?> getALlCarts(Pageable pageable) {
        PageResponseDto pageResponseDto = cartService.getAllCarts(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCart(@RequestBody CartRequestDTO requestDTO) {
        CartResponseDto cartResponseDto = cartService.insertCart(requestDTO);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{cart-id}")
    public ResponseEntity<?> getCartByCartId(@PathVariable("cart-id") int cartId) {
        CartResponseDto cartResponseDto = cartService.getCartByCartId(cartId);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{cart-id}")
    public ResponseEntity<?> deleteCartByCartId(@PathVariable("cart-id") int cartId) {
        cartService.deleteCartByCartId(cartId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Cart Id: ").append(cartId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
