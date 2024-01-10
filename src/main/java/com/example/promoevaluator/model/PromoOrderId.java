package com.example.promoevaluator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoOrderId {
    private String promoId;
    private String customerId;        
}
