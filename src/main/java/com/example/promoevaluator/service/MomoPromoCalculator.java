package com.example.promoevaluator.service;

import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Order;

// Team 2
@Service
public class MomoPromoCalculator {
    public void calculate(Order order) {
        System.out.println("MomoPromoCalculator.calculate()");
    }
}
