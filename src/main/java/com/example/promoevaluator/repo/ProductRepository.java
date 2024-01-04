package com.example.promoevaluator.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
}
