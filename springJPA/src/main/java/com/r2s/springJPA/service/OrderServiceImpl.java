package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.request.CreateOrderRequestDTO;
import com.r2s.springJPA.dto.request.UpdateOrderRequestDto;
import com.r2s.springJPA.dto.response.OrderResponseDto;
import com.r2s.springJPA.dto.response.PageResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Orders;
import com.r2s.springJPA.entity.Product;
import com.r2s.springJPA.mapper.OrderMapper;
import com.r2s.springJPA.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageResponseDto getAllOrders(Pageable pageable) {
        Page<Orders> ordersPage = orderRepository.findAll(pageable)
                .orElseThrow(() -> new RuntimeException("Can't get Products by paging"));

        PageResponseDto pageResponseDto = new PageResponseDto();
        pageResponseDto.setPage(ordersPage.getNumber());
        pageResponseDto.setSize(ordersPage.getSize());
        pageResponseDto.setTotalPages(ordersPage.getTotalPages());
        pageResponseDto.setTotalRecord(ordersPage.getTotalElements());
        List<OrderResponseDto> orderResponseDtos = orderMapper.convertEntitiesResponseDtos(ordersPage.getContent());
        pageResponseDto.setData(orderResponseDtos);
        return pageResponseDto;
    }

    @Override
    public OrderResponseDto insertOrder(CreateOrderRequestDTO requestDTO) {
        try {
            Orders order = new Orders();
            order.setCartId(requestDTO.getCartId());
            order.setTotalCost(requestDTO.getTotalCost());
            order.setStatus(requestDTO.getStatus());
            return orderMapper.convertEntityResponseDto(orderRepository.save(order));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderResponseDto updateOrder(int orderId, UpdateOrderRequestDto requestDto) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order doesn't exist"));
        order.setTotalCost(requestDto.getTotalCost());
        order.setStatus(requestDto.getStatus());

        return orderMapper.convertEntityResponseDto(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto getOrderById(int orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order doesn't exist"));

        return orderMapper.convertEntityResponseDto(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order doesn't exist"));
        order.setDeleted(true);

        orderRepository.save(order);
    }
}
