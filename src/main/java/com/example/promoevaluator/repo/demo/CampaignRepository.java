package com.example.promoevaluator.repo.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.demo.Campaign;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
    
}
