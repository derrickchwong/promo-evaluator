package com.example.promoevaluator.repo.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.demo.ProductGroup;

public interface ProductGroupRepository extends MongoRepository<ProductGroup, String> {
    
}
