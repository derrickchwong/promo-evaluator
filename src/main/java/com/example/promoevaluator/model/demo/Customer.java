package com.example.promoevaluator.model.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Customer {
    @Id
    private String id; 
    private String name;

    // list of available campaigns for the customer to join, key is campaign and value is the remaining amount 
    private Map<String, Integer> availableCampaigns;

    @DocumentReference(lazy = true)
    private List<Order> orders;

    public void addOrder(Order order) {
        if(orders == null)
            orders = new ArrayList<>();
        else
            orders.add(order);
    }
    
    public void cancelOrder(Order order){
        if(orders != null)
            orders.remove(order);
    }

    // update the available campaigns for the customer)
    public void updateAvailableCampaign(Campaign campaign, int amount) {
        if(availableCampaigns == null)
            availableCampaigns = new HashMap<>();    
        availableCampaigns.put(campaign.getId(), amount);
    }
}
