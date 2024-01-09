package com.example.promoevaluator.repo.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.demo.Merchant;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    
}
