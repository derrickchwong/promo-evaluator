package com.example.promoevaluator.repo.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.demo.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    
}
