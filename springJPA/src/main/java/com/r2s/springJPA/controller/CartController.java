package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.CreateCartRequestDTO;
import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Cart;
import com.r2s.springJPA.entity.Users;
import com.r2s.springJPA.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @GetMapping
    public ResponseEntity<?> getALlCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCart(@RequestBody CreateCartRequestDTO requestDTO) {
        Cart cart = cartService.insertCart(requestDTO);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
