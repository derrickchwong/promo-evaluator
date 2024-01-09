package com.example.promoevaluator.model.demo;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Document
@Builder
@Jacksonized
@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String description;
    private Integer originalPrice;
    private String productGroupId;

}
