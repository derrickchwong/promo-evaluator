package com.example.promoevaluator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Document
@Builder
@Jacksonized
@Getter
@Setter
public class Order {
    @Id
    private String id;
    private String customerId;
    private List<OrderItem> orderItems;

    public void addOrderItem(OrderItem orderItem){
        
        if(orderItems == null)
            orderItems = new ArrayList<>();
        orderItems.add(orderItem);
    }

    @Override
    public boolean equals(Object obj) {
        Order o = (Order) obj;
        return o.id.equals(this.id) && o.customerId.equals(this.customerId);    
    }
}
