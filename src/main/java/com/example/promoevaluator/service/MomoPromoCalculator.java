package com.example.promoevaluator.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Order;

// Team 2
@Service
public class MomoPromoCalculator {

    private final MongoTemplate mongoTemplate;

    public MomoPromoCalculator(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Team 2

    public void calculate(Order order) {
        System.out.println("MomoPromoCalculator.calculate()");
        

    }
}
