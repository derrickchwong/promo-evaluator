package com.example.promoevaluator.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.Merchant;

public interface MerchantRepository extends MongoRepository<Merchant, String> {
    
}
