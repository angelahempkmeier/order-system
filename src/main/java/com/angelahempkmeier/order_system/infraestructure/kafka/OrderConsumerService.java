package com.angelahempkmeier.order_system.infraestructure.kafka;

import com.angelahempkmeier.order_system.application.service.OrderService;
import com.angelahempkmeier.order_system.domain.model.Order;
import com.angelahempkmeier.order_system.domain.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class OrderConsumerService {

    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderConsumerService.class);


    public OrderConsumerService(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "orders", groupId = "order-group")
    public void consume(Order order) {
        logger.info("Received message from Kafka: {}", order);
        orderService.updateOrderStatus(order.getId(), OrderStatus.ENVIADO_TRANSPORTADORA);
        logger.info("Order status updated to ENVIADO_TRANSPORTADORA for Order ID: {}", order.getId());
    }
}
