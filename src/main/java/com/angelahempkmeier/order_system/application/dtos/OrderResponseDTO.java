package com.angelahempkmeier.order_system.application.dtos;

import com.angelahempkmeier.order_system.domain.model.Order;
import com.angelahempkmeier.order_system.domain.model.OrderStatus;

public record OrderResponseDTO(String id, String product, int quantity, OrderStatus status) {
    public static OrderResponseDTO from(Order order){
        return new OrderResponseDTO(order.getId(), order.getProduct(), order.getQuantity(), order.getStatus());
    }
}
