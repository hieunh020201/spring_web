package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateOrderRequestDTO;
import com.r2s.springJPA.entity.Orders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {
    public List<Orders> getAllOrders();

    public Orders insertOrder(CreateOrderRequestDTO requestDTO);
}
