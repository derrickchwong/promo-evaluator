package com.example.promoevaluator.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude="productGroup")
public class Product {
    private String id;
    private String name;
    private String description;
    private Integer price;
    @DocumentReference
    private ProductGroup productGroup;
}
