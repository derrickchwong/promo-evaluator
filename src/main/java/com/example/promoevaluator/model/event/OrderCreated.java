package com.example.promoevaluator.model.event;

import java.util.ArrayList;
import java.util.List;

import com.example.promoevaluator.model.OrderItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCreated {
    
    private String eventId;
    private String orderId;
    private String customerId;
    private List<OrderItem> orderItems;

    public void addOrderItem(OrderItem orderItem){   
        if(orderItems == null)
            orderItems = new ArrayList<>();
        orderItems.add(orderItem);
    }
}
