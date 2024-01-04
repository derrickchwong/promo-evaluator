package com.example.promoevaluator.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Document
@Jacksonized
@Builder
@Getter
@Setter
@Data
public class Merchant {
    @Id
    private String id;
    private String name;
    @DocumentReference
    private List<Campaign> campaigns;
    @DocumentReference
    private List<ProductGroup> productGroups;

    public void addCampaign(Campaign campaign){
        if(this.campaigns == null)
            this.campaigns = new ArrayList<>();
        this.campaigns.add(campaign);
    }

    public void addProductGroup(ProductGroup productGroup){
        if(this.productGroups == null)
            this.productGroups = new ArrayList<>();
        this.productGroups.add(productGroup);
    }
}
