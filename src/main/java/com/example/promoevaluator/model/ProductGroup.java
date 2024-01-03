package com.example.promoevaluator.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"merchant", "products", "campaigns"})
public class ProductGroup {
    
    @Id
    private String id;
    private String name;
    private String description;
    @DocumentReference
    private Merchant merchant;

    @DocumentReference
    private List<Product> products;

    // having a list of related campaigns here can save compute time 
    @DocumentReference
    private List<Campaign> campaigns;
    
}
