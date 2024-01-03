package com.example.promoevaluator.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;

@Data
@Document
public class Customer {
    @Id
    private String id; 
    private String name;

    // list of available campaigns for the customer to join, key is campaign and value is the remaining amount 
    @DocumentReference(lazy = true)
    private Map<Campaign, Integer> availableCampaigns;

    @DocumentReference(lazy = true)
    private List<Order> orders;

    @DocumentReference(lazy = true)
    private List<Campaign> joinedCampaigns;

    public void updateAvailableCampaign(Campaign campaign, int amount) {
        if(availableCampaigns == null)
            availableCampaigns = new HashMap<>();    
        availableCampaigns.put(campaign, amount);
    }
}
