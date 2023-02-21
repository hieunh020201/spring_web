package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.request.CreateOrderRequestDTO;
import com.r2s.springJPA.dto.request.UpdateOrderRequestDto;
import com.r2s.springJPA.dto.response.OrderResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Orders;
import com.r2s.springJPA.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getALlOrders(Pageable pageable) {
        PageResponseDto pageResponseDto = orderService.getAllOrders(pageable);
        return new ResponseEntity<>(pageResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertOrder(@RequestBody CreateOrderRequestDTO requestDTO) {
        OrderResponseDto orderResponseDto = orderService.insertOrder(requestDTO);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{order-id}")
    public ResponseEntity<?> updateOrder(@PathVariable("order-id") int orderId, @RequestBody UpdateOrderRequestDto requestDto) {
        OrderResponseDto orderResponseDto = orderService.updateOrder(orderId, requestDto);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<?> getOrderByOrderId(@PathVariable("order-id") int orderId) {
        OrderResponseDto orderResponseDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity<?> deleteOrderByOrderId(@PathVariable("order-id") int orderId) {
        orderService.deleteOrder(orderId);
        StringBuilder response = new StringBuilder();
        response.append("Delete Order Id: ").append(orderId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
