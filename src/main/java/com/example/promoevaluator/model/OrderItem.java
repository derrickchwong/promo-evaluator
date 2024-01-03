package com.example.promoevaluator.model;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Data
public class OrderItem {
    private Integer quantity;
    @DocumentReference
    private Product product;
    private Integer price;
}
