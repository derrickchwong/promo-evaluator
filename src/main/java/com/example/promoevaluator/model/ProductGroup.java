package com.example.promoevaluator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Document
@Builder
@Jacksonized
@Getter
@Setter
public class ProductGroup {
    
    @Id
    private String id;
    private String name;
    private String description;
    @DocumentReference
    private List<Product> products;
    @DocumentReference
    private List<Campaign> campaigns;

    public void addProduct(Product product) {
        if(products == null)
            products = new ArrayList<>();
        products.add(product);
    }

    public void deleteProduct(Product product) {
        if(products != null)
            products.remove(product);
    }

    public void addCampaign(Campaign campaign) {
        if(campaigns == null)
            campaigns = new ArrayList<>();
        campaigns.add(campaign);
    }
}
