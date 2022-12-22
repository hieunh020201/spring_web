package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.OrderRequestDTO;
import r2s.com.spring.web.dto.response.CategoryResponseDTO;
import r2s.com.spring.web.dto.response.OrderListResponseDTO;
import r2s.com.spring.web.dto.response.OrderResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @PostMapping
    public ResponseEntity insertOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append("Order has: ID cart: ").append(orderRequestDTO.getCartId())
                .append(", total cost: ").append(orderRequestDTO.getTotalCost())
                .append(", status: ").append(orderRequestDTO.getStatus()).toString();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllOrders() {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(1);
        orderResponseDTO.setCartId(2);
        orderResponseDTO.setStatus("Ordered");
        orderResponseDTO.setTotalCost(10000);

        OrderResponseDTO orderResponseDTO1 = new OrderResponseDTO();
        orderResponseDTO1.setId(2);
        orderResponseDTO1.setCartId(2);
        orderResponseDTO1.setStatus("Ordered");
        orderResponseDTO1.setTotalCost(40000);

        List<OrderResponseDTO> orderList = new ArrayList<>();
        orderList.add(orderResponseDTO);
        orderList.add(orderResponseDTO1);

        OrderListResponseDTO response = new OrderListResponseDTO();
        response.setResponseDTOList(orderList);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
