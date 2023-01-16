package com.r2s.springJPA.mapper;

import com.r2s.springJPA.dto.response.OrderResponseDto;
import com.r2s.springJPA.dto.response.ProductResponseDto;
import com.r2s.springJPA.entity.Orders;
import com.r2s.springJPA.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public List<OrderResponseDto> convertEntitiesResponseDtos(List<Orders> orders) {
        return orders.stream().map(this::convertEntityResponseDto).toList();
    }

    public OrderResponseDto convertEntityResponseDto(Orders order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        BeanUtils.copyProperties(order, orderResponseDto);
        return orderResponseDto;
    }
}
