package com.angelahempkmeier.order_system.application.service;

import com.angelahempkmeier.order_system.application.dtos.OrderRequestDTO;
import com.angelahempkmeier.order_system.application.dtos.OrderResponseDTO;
import com.angelahempkmeier.order_system.application.exception.OrderNotFoundException;
import com.angelahempkmeier.order_system.domain.model.Order;
import com.angelahempkmeier.order_system.domain.model.OrderStatus;
import com.angelahempkmeier.order_system.infraestructure.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final KafkaTemplate<String, Order> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderService(OrderRepository repository, KafkaTemplate<String, Order> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO){
        Order order = new Order(orderRequestDTO.product(), orderRequestDTO.quantity());
        order.setStatus(OrderStatus.AGUARDANDO_ENVIO);
        Order savedOrder = repository.save(order);
        logger.info("Order created: {}", savedOrder);
        kafkaTemplate.send("orders", savedOrder);
        logger.info("Message sent for Kafka: {}", savedOrder);
        return OrderResponseDTO.from(order);
    }

    public OrderResponseDTO getById(String id){
        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return OrderResponseDTO.from(order);
    }

    public void updateOrderStatus(String id, OrderStatus status) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id.toString()));
        order.setStatus(status);
        repository.save(order);
    }
}
