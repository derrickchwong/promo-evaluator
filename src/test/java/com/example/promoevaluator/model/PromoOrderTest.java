package com.example.promoevaluator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PromoOrderTest {
    
    @Test
    public void testPromoOrderId() throws JsonProcessingException{
        

        // {
        //     "promoId": "20240105142141577",
        //     "customerId": "customer000001"
        // }
        
        PromoOrder promoOrder = new PromoOrder();
        PromoOrderId promoOrderId = new PromoOrderId("20240105142141577", "customer000001");
        promoOrder.setId(promoOrderId);

        ObjectMapper objectMapper = new ObjectMapper();
        
        String json = objectMapper.writeValueAsString(promoOrder);

        String expected = "{\"promoId\":\"20240105142141577\",\"customerId\":\"customer000001\",\"orders\":null}";

        assertEquals(expected, json);
    }
    
}
