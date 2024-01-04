package com.example.promoevaluator.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    
}
