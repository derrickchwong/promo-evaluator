package com.example.promoevaluator.model.demo;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Document
@Builder
@Jacksonized
@Getter
@Setter
@Data
public class Campaign {

    private String id;   
    private String merchantId;
    private Instant startDate;    
    private Instant endDate;    
    private Integer quota;
    private Integer remaining;
    private Map<String, Integer> productGroupAmountMap;
    public void addProductGroupAmount(String productGroup, Integer amount) {
        if(productGroupAmountMap == null)
            productGroupAmountMap = new HashMap<>();
        productGroupAmountMap.put(productGroup, amount);
    }
    
}
