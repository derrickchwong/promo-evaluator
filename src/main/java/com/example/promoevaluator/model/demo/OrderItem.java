package com.example.promoevaluator.model.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer quantity;
    private String productId;
    private Integer price;
}
