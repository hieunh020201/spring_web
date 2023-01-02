package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CreateCartRequestDTO;
import r2s.com.spring.web.dto.request.UpdateCartDetailRequestDTO;
import r2s.com.spring.web.dto.response.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @PostMapping
    public ResponseEntity insertCart(@RequestBody CreateCartRequestDTO cartRequestDTO) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setId(1);
        cartResponseDTO.setUserId(cartRequestDTO.getUserId());
        cartResponseDTO.setCreatedAt(cartRequestDTO.getCreateAt());

        CartDetailListResponseDTO response = new CartDetailListResponseDTO();
        List<CartDetailResponseDTO> cartDetailList = new ArrayList<>();
        cartRequestDTO.getCartDetailRequestDTOList().stream().forEach(item -> {
            CartDetailResponseDTO cartDetailResponseDTO  = new CartDetailResponseDTO();
            cartDetailResponseDTO.setIdCart(1);
            cartDetailResponseDTO.setName(item.getName());
            cartDetailResponseDTO.setProductId(item.getProductId());
            cartDetailResponseDTO.setQuantity(item.getQuantity());
            cartDetailResponseDTO.setCost(item.getCost());
            cartDetailResponseDTO.setStatus(item.getStatus());
            cartDetailList.add(cartDetailResponseDTO);
        });

        response.setCartDetailResponseDTOList(cartDetailList);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity getAllCartDetail(@RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "sort", required = false) String type_sort) {
        CartDetailResponseDTO cartDetailResponseDTO = new CartDetailResponseDTO();
        cartDetailResponseDTO.setIdCart(1);
        cartDetailResponseDTO.setName("Ao len");
        cartDetailResponseDTO.setCost(100000);
        cartDetailResponseDTO.setQuantity(5);
        cartDetailResponseDTO.setProductId(1);
        cartDetailResponseDTO.setStatus("Ordered");

        CartDetailResponseDTO cartDetailResponseDTO1 = new CartDetailResponseDTO();
        cartDetailResponseDTO1.setIdCart(2);
        cartDetailResponseDTO1.setName("Dong ho");
        cartDetailResponseDTO1.setCost(200000);
        cartDetailResponseDTO1.setQuantity(5);
        cartDetailResponseDTO1.setProductId(3);
        cartDetailResponseDTO1.setStatus("Ordered");

        List<CartDetailResponseDTO> cartDetailResponseDTOList = new ArrayList<>();
        cartDetailResponseDTOList.add(cartDetailResponseDTO);
        cartDetailResponseDTOList.add(cartDetailResponseDTO1);

        PagingCartDetailListResponseDTO response = new PagingCartDetailListResponseDTO();
        response.setCartDetailList(cartDetailResponseDTOList);
        response.setSize(size);
        response.setPage(page);
        response.setSort(type_sort);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{cart-id}")
    public ResponseEntity getDetailCartByCartId(@PathVariable(value = "cart-id") int cartId) {
        List<CartDetailResponseDTO> cartDetailList = new ArrayList<>();
        CartDetailResponseDTO cartDetailResponseDTO = new CartDetailResponseDTO();
        cartDetailResponseDTO.setIdCart(cartId);
        cartDetailResponseDTO.setName("Ao len");
        cartDetailResponseDTO.setCost(100000);
        cartDetailResponseDTO.setQuantity(5);
        cartDetailResponseDTO.setProductId(1);
        cartDetailResponseDTO.setStatus("Ordered");
        cartDetailList.add(cartDetailResponseDTO);

        CartDetailResponseDTO cartDetailResponseDTO1 = new CartDetailResponseDTO();
        cartDetailResponseDTO1.setIdCart(cartId);
        cartDetailResponseDTO1.setName("Dong ho");
        cartDetailResponseDTO1.setCost(200000);
        cartDetailResponseDTO1.setQuantity(5);
        cartDetailResponseDTO1.setProductId(3);
        cartDetailResponseDTO1.setStatus("Ordered");
        cartDetailList.add(cartDetailResponseDTO1);

        CartDetailListResponseDTO response = new CartDetailListResponseDTO();
        response.setCartDetailResponseDTOList(cartDetailList);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{cart-id}")
    public ResponseEntity updateCart(@PathVariable(value = "cart-id") int cartId,
                                     @RequestBody UpdateCartDetailRequestDTO updateCartDetailRequestDTO) {
        List<CartDetailResponseDTO> cartDetailList = new ArrayList<>();
        updateCartDetailRequestDTO.getCartDetailRequestDTOList().stream().forEach(item -> {
            CartDetailResponseDTO cartDetailResponseDTO = new CartDetailResponseDTO();
            cartDetailResponseDTO.setIdCart(cartId);
            cartDetailResponseDTO.setName(item.getName());
            cartDetailResponseDTO.setQuantity(item.getQuantity());
            cartDetailResponseDTO.setProductId(item.getProductId());
            cartDetailResponseDTO.setCost(item.getCost());
            cartDetailResponseDTO.setStatus(item.getStatus());
            cartDetailList.add(cartDetailResponseDTO);
        });

        CartDetailListResponseDTO response = new CartDetailListResponseDTO();
        response.setCartDetailResponseDTOList(cartDetailList);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{cart-id}")
    public ResponseEntity deleteCart(@PathVariable(value = "cart-id") int cartId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
