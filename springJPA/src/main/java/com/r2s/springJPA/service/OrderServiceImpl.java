package com.r2s.springJPA.service;

import com.r2s.springJPA.dto.CreateOrderRequestDTO;
import com.r2s.springJPA.entity.Orders;
import com.r2s.springJPA.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Orders> getAllOrders() {
        return (List<Orders>) orderRepository.findAll();
    }

    @Override
    public Orders insertOrder(CreateOrderRequestDTO requestDTO) {
        try {
            Orders order = new Orders();
            order.setCartId(requestDTO.getCartId());
            order.setTotalCost(requestDTO.getTotalCost());
            order.setStatus(requestDTO.getStatus());
            return orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
