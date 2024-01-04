package com.example.promoevaluator.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
