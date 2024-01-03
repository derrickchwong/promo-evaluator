package com.example.promoevaluator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Order {
    @Id
    private String id;
    private Customer customer;
    private List<OrderItem> orderItems;

    public void addOrderItem(OrderItem orderItem){
        
        if(orderItems == null)
            orderItems = new ArrayList<>();
        orderItems.add(orderItem);
    }
}
