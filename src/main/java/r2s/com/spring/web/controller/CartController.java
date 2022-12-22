package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CartDetailRequestDTO;
import r2s.com.spring.web.dto.request.CreateCartRequestDTO;
import r2s.com.spring.web.dto.response.CartDetailListResponseDTO;
import r2s.com.spring.web.dto.response.CartDetailResponseDTO;
import r2s.com.spring.web.dto.response.CartResponseDTO;
import r2s.com.spring.web.dto.response.CategoryResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @PostMapping
    public ResponseEntity insertCart(@RequestBody CreateCartRequestDTO cartRequestDTO) {
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        cartResponseDTO.setId(1);
        cartResponseDTO.setUserId(2);

        CartDetailRequestDTO cartDetailRequestDTO = new CartDetailRequestDTO();
        cartDetailRequestDTO.setName("Ao len");
        cartDetailRequestDTO.setCost(100000);
        cartDetailRequestDTO.setQuantity(5);
        cartDetailRequestDTO.setProductId(1);
        cartDetailRequestDTO.setStatus("Ordered");

        CartDetailRequestDTO cartDetailRequestDTO1 = new CartDetailRequestDTO();
        cartDetailRequestDTO1.setName("Dong ho");
        cartDetailRequestDTO1.setCost(200000);
        cartDetailRequestDTO1.setQuantity(5);
        cartDetailRequestDTO1.setProductId(3);
        cartDetailRequestDTO1.setStatus("Ordered");

        List<CartDetailRequestDTO> listItem = new ArrayList<>();
        listItem.add(cartDetailRequestDTO);
        listItem.add(cartDetailRequestDTO1);

        cartResponseDTO.setCartDetailRequestDTOList(listItem);

        return new ResponseEntity(cartResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCartDetail() {
        CartDetailResponseDTO cartDetailResponseDTO = new CartDetailResponseDTO();
        cartDetailResponseDTO.setId(1);
        cartDetailResponseDTO.setName("Ao len");
        cartDetailResponseDTO.setCost(100000);
        cartDetailResponseDTO.setQuantity(5);
        cartDetailResponseDTO.setProductId(1);
        cartDetailResponseDTO.setStatus("Ordered");

        CartDetailResponseDTO cartDetailResponseDTO1 = new CartDetailResponseDTO();
        cartDetailResponseDTO1.setId(2);
        cartDetailResponseDTO1.setName("Dong ho");
        cartDetailResponseDTO1.setCost(200000);
        cartDetailResponseDTO1.setQuantity(5);
        cartDetailResponseDTO1.setProductId(3);
        cartDetailResponseDTO1.setStatus("Ordered");

        List<CartDetailResponseDTO> cartDetailResponseDTOList = new ArrayList<>();
        cartDetailResponseDTOList.add(cartDetailResponseDTO);
        cartDetailResponseDTOList.add(cartDetailResponseDTO1);

        CartDetailListResponseDTO response = new CartDetailListResponseDTO();
        response.setCartDetailResponseDTOList(cartDetailResponseDTOList);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
