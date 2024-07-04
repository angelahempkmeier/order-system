package com.angelahempkmeier.order_system.application.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String id){
        super("Order not found for the id: " + id);
    }
}
