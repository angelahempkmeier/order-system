package com.angelahempkmeier.order_system.application.controller;

import com.angelahempkmeier.order_system.application.dtos.OrderRequestDTO;
import com.angelahempkmeier.order_system.application.dtos.OrderResponseDTO;
import com.angelahempkmeier.order_system.application.service.OrderService;
import com.angelahempkmeier.order_system.domain.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO createdOrder = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable String id) {
        OrderResponseDTO order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }
}
