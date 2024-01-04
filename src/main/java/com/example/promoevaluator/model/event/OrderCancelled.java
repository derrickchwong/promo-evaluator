package com.example.promoevaluator.model.event;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCancelled {
    
    private String eventId;
    private String orderId;
    
}
