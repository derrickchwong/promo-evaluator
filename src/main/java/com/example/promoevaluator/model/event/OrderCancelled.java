package com.example.promoevaluator.model.event;

import lombok.Data;

@Data
public class OrderCancelled extends OrderEvent{
    
    public OrderCancelled() {
        super();
    }
    
}
