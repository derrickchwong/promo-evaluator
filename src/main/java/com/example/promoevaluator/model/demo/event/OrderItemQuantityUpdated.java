package com.example.promoevaluator.model.demo.event;

import lombok.Data;

@Data
public class OrderItemQuantityUpdated extends OrderEvent{
    private String productId;
    private Integer newQuantity;
    private Integer previousQuantity;

    public OrderItemQuantityUpdated() {
        super();
    }
}
