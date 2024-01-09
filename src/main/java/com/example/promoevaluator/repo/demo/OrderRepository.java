package com.example.promoevaluator.repo.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.demo.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    
}
