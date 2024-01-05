package com.example.promoevaluator.model.event;

import java.util.ArrayList;
import java.util.List;

import com.example.promoevaluator.model.OrderItem;

import lombok.Data;

@Data
public class OrderCreated extends OrderEvent{
    
    private String customerId;
    private List<OrderItem> orderItems;

    public void addOrderItem(OrderItem orderItem){   
        if(orderItems == null)
            orderItems = new ArrayList<>();
        orderItems.add(orderItem);
    }

    public OrderCreated() {
        super();
    }
}
