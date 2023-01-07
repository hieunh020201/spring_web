package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateOrderRequestDTO;
import com.r2s.springJPA.dto.request.UpdateOrderRequestDto;
import com.r2s.springJPA.dto.response.OrderResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

public interface OrderService {
    public PageResponseDto getAllOrders(Pageable pageable);

    public OrderResponseDto insertOrder(CreateOrderRequestDTO requestDTO);

    public OrderResponseDto updateOrder(int orderId, UpdateOrderRequestDto requestDto);

    public OrderResponseDto getOrderById(int orderId);

    public void deleteOrder(int orderId);
}
