package com.example.promoevaluator.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.ProductGroup;

public interface ProductGroupRepository extends MongoRepository<ProductGroup, String> {
    
}
