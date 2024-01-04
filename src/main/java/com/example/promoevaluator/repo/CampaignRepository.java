package com.example.promoevaluator.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.promoevaluator.model.Campaign;

public interface CampaignRepository extends MongoRepository<Campaign, String> {
    
}
