package com.example.promoevaluator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Getter
@Setter
public class OrderItem {
    private Integer quantity;
    private String productId;
    private Integer price;
}
