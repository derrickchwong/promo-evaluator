package com.example.promoevaluator.service;

import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.PromoOrder;
import com.example.promoevaluator.repo.PromoOrderRepository;

// Team 2
@Service
public class MomoPromoCalculator {

    private PromoOrderRepository promoOrderRepository;
    
    // constructor 
    public MomoPromoCalculator(PromoOrderRepository promoOrderRepository) {
        this.promoOrderRepository = promoOrderRepository;
    }

    // Team 2

    public void calculate(Order order) {
        System.out.println("MomoPromoCalculator.calculate()");
        
        // To save PromoOrder 
        PromoOrder promoOrder = new PromoOrder();
        promoOrderRepository.save(promoOrder);

        // 


    }
}
