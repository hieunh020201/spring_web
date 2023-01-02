package r2s.com.spring.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r2s.com.spring.web.dto.request.CreateOrderRequestDTO;
import r2s.com.spring.web.dto.request.UpdateOrderRequestDTO;
import r2s.com.spring.web.dto.response.OrderResponseDTO;
import r2s.com.spring.web.dto.response.PagingOrderListResponseDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @PostMapping
    public ResponseEntity insertOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {

        StringBuilder stringBuilder = new StringBuilder();
        String response = stringBuilder.append("Order has: ID cart: ").append(createOrderRequestDTO.getCartId())
                .append(", total cost: ").append(createOrderRequestDTO.getTotalCost())
                .append(", status: ").append(createOrderRequestDTO.getStatus()).toString();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity getAllOrders(@RequestParam(value = "page", required = false) Integer page,
                                       @RequestParam(value = "size", required = false) Integer size,
                                       @RequestParam(value = "sort", required = false) String type_sort) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setId(1);
        orderResponseDTO.setCartId(1);
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

        PagingOrderListResponseDTO response = new PagingOrderListResponseDTO();
        response.setOrderList(orderList);
        response.setPage(page);
        response.setSize(size);
        response.setSort(type_sort);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getDetailOrderByOrderId(@PathVariable(value = "order-id") int orderId) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(orderId);
        response.setCartId(1);
        response.setStatus("Ordered");
        response.setTotalCost(10000);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("/{order-id}")
    public ResponseEntity updateOrder(@PathVariable(value = "order-id") int orderId,
                                     @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO) {
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(orderId);
        response.setCartId(1);
        response.setStatus(updateOrderRequestDTO.getStatus());
        response.setTotalCost(updateOrderRequestDTO.getTotalCost());

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable(value = "order-id") int orderId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
