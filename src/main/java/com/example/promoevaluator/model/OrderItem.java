package com.example.promoevaluator.model;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
    @DocumentReference
    private Product product;
    private Integer price;
}
