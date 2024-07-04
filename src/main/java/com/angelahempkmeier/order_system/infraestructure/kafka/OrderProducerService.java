package com.angelahempkmeier.order_system.infraestructure.kafka;

import com.angelahempkmeier.order_system.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderProducerService.class);


    public OrderProducerService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        logger.info("Sending order to Kafka: {}", order);
        kafkaTemplate.send("orders", order);
        logger.info("Order sent to Kafka: {}", order);
    }
}
