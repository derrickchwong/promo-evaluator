package com.example.promoevaluator.model.demo.event;

import lombok.Data;

@Data
public class OrderCancelled extends OrderEvent{
    
    public OrderCancelled() {
        super();
    }
    
}
