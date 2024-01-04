package com.example.promoevaluator.model.event;

import lombok.Data;

@Data
public class OrderCancelled {
    
    private String eventId;
    private String orderId;
    
}
