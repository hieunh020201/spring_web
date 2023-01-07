package com.r2s.springJPA.controller;

import com.r2s.springJPA.dto.CreateOrderRequestDTO;
import com.r2s.springJPA.dto.CreateUserRequestDTO;
import com.r2s.springJPA.entity.Orders;
import com.r2s.springJPA.entity.Users;
import com.r2s.springJPA.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getALlOrders() {
        List<Orders> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertOrder(@RequestBody CreateOrderRequestDTO requestDTO) {
        Orders order = orderService.insertOrder(requestDTO);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
