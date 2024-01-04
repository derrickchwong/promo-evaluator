package com.example.promoevaluator.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.repo.CustomerRepository;
import com.example.promoevaluator.service.PromoEvaluator;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class CustomerController {
    
    private final CustomerRepository customerRepository;
    private final PromoEvaluator promoEvaluator;
    
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
        if(customer.getId() == null || customer.getId().isBlank())
            customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    // @PostMapping("/orders")
    // public Customer addOrder(@RequestBody Order order) {
    //     return promoEvaluator.orderReceiver(order);
    // }


}


