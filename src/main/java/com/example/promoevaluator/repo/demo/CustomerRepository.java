package com.example.promoevaluator.repo.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.demo.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
