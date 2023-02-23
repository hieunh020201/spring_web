package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.*;
import com.r2s.springJPA.dto.response.*;
import com.r2s.springJPA.service.AddressService;
import com.r2s.springJPA.service.CartLineItemService;
import com.r2s.springJPA.service.CartService;
import com.r2s.springJPA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartLineItemService cartLineItemService;

    @GetMapping("/users")
    public ResponseEntity<?> getALlUsers(Pageable pageable) {
        PageResponseDto pageResponseDto = userService.getAllUsers(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("user-id") int userId) {
        UserResponseDto userResponseDto = userService.getUserById(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> insertUser(@RequestBody CreateUserRequestDTO requestDTO) {
        UserResponseDto user = userService.insertUser(requestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{user-id}")
    public ResponseEntity<?> updateUserById(@PathVariable("user-id") int userId, @RequestBody UpdateUserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userId, requestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{user-id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("user-id") int userId) {
        userService.deleteUser(userId);
        StringBuilder response = new StringBuilder();
        response.append("Delete User Id: ").append(userId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/{user-id}/address")
    public ResponseEntity<?> insertAddressByUser(@PathVariable("user-id") int userId, @RequestBody CreateAddressByUserRequestDTO requestDTO) {
        UserAddressResponseDto addressResponseDto = addressService.insertAddressByUser(userId, requestDTO);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}/addresses")
    public ResponseEntity<?> getListAddressesByUser(@PathVariable("user-id") int userId) {
        AddressByUserResponseDto addressResponseDto = addressService.getListAddressesByUser(userId);
        return new ResponseEntity<>(addressResponseDto, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}/address/{address-id}")
    public ResponseEntity<?> getAddressByUser(@PathVariable("user-id") int userId, @PathVariable("address-id") int addressId) {
        UserAddressResponseDto userResponseDto = addressService.getAddressByUser(userId, addressId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PutMapping("/user/{user-id}/address/{address-id}")
    public ResponseEntity<?> updateAddressByUser(@PathVariable("user-id") int userId, @PathVariable("address-id") int addressId, @RequestBody UpdateAddressByUserRequestDto requestDTO) {
        UserAddressResponseDto userResponseDto = addressService.updateAddressByUser(userId, addressId, requestDTO);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{user-id}/address/{address-id}")
    public ResponseEntity<?> deleteAddressByUser(@PathVariable("user-id") int userId, @PathVariable("address-id") int addressId) {
        addressService.deleteAddressByUser(userId, addressId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Address of User Id: ").append(userId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{user-id}/carts")
    public ResponseEntity<?> getAllCartsByUser(@PathVariable("user-id") int userId, Pageable pageable) {
        PageResponseDto pageResponseDto = cartService.getAllCartsByUser(userId, pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}/cart/{cart-id}")
    public ResponseEntity<?> getCartByUser(@PathVariable("user-id") int userId, @PathVariable("cart-id") int cartId) {
        CartResponseDto cartResponseDto = cartService.getCartByUser(userId, cartId);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @PostMapping("/user/{user-id}/cart")
    public ResponseEntity<?> insertCartByUser(@PathVariable("user-id") int userId) {
        CartResponseDto cartResponseDto = cartService.insertCartByUser(userId);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @PutMapping("/user/{user-id}/cart/{cart-id}")
    public ResponseEntity<?> updateCartByUser(@PathVariable("user-id") int userId, @PathVariable("cart-id") int cartId) {
        CartResponseDto cartResponseDto = cartService.updateCartByUser(userId, cartId);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{user-id}/cart/{cart-id}")
    public ResponseEntity<?> deleteCartByUser(@PathVariable("user-id") int userId, @PathVariable("cart-id") int cartId) {
        cartService.deleteCartByUser(userId, cartId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Cart of User Id: ").append(userId);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{user-id}/cart/{cart-id}/details")
    public ResponseEntity<?> getDetailsCartByUser(@PathVariable("user-id") int userId, @PathVariable("cart-id") int cartId) {
        CartResponseDto cartResponseDto = cartLineItemService.getDetailsCartByUser(userId, cartId);

        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @GetMapping("/user/{user-id}/cart/{cart-id}/detail/{detail-id}")
    public ResponseEntity<?> getDetailCartByUser(@PathVariable("user-id") int userId, @PathVariable("cart-id") int cartId, @PathVariable("detail-id") int detailId) {
        CartLineItemResponseDto cartResponseDto = cartLineItemService.getDetailCartByUser(userId, cartId, detailId);

        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @PostMapping("/user/{user-id}/cart/{cart-id}/detail")
    public ResponseEntity<?> insertDetailCartByUser(@PathVariable("user-id") int userId,
                                                    @PathVariable("cart-id") int cartId,
                                                    @RequestBody CreateCartLineItemRequestDto requestDto) {
        CartResponseDto cartResponseDto = cartLineItemService.insertCartLineItemByUser(userId, cartId, requestDto);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @PutMapping("/user/{user-id}/cart/{cart-id}/detail/{detail-id}")
    public ResponseEntity<?> updateDetailCartByUser(@PathVariable("user-id") int userId,
                                              @PathVariable("cart-id") int cartId,
                                              @PathVariable("detail-id") int detailId,
                                              @RequestBody UpdateCartLineItemRequestDto requestDto) {
        CartResponseDto cartResponseDto = cartLineItemService.updateCartLineItemByUser(userId, cartId, detailId, requestDto);
        return new ResponseEntity<>(cartResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/user/{user-id}/cart/{cart-id}/detail/{detail-id}")
    public ResponseEntity<?> deleteDetailCartByUser(@PathVariable("user-id") int userId,
                                                    @PathVariable("cart-id") int cartId,
                                                    @PathVariable("detail-id") int detailId) {
        cartLineItemService.deleteCartLineItemByUser(userId, cartId, detailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
