package com.angelahempkmeier.order_system.infraestructure.repository;

import com.angelahempkmeier.order_system.domain.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
