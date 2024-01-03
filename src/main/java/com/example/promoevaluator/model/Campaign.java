package com.example.promoevaluator.model;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"participants", "productGroupAmountMap", "merchant"})
public class Campaign {
    @Id
    private String id;   
    @DocumentReference
    private Merchant merchant;
    private Instant startDate;    
    private Instant endDate;    
    private Integer quota;
    private Integer remaining;

    @DocumentReference
    private Map<ProductGroup, Integer> productGroupAmountMap;
    
    @DocumentReference(lazy = true)
    private List<Customer> participants;
}
