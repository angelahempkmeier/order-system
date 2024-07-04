package com.angelahempkmeier.order_system.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String product;
    private int quantity;
    private OrderStatus status;

    public Order(){

    }

    public Order(String product, int quantity){
        this.id = generateRandomString();
        this.product = product;
        this.quantity = quantity;
    }

    public Order(String id, String product, int quantity, OrderStatus status) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }

    private String generateRandomString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH") ;
        String formattedDateTime = now.format(formatter);

        Random random = new Random();
        int randomNumber = random.nextInt(1000);

        return formattedDateTime + String.format("%03d", randomNumber);
    }
}
