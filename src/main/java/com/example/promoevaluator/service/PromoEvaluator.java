package com.example.promoevaluator.service;

import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.OrderItem;
import com.example.promoevaluator.model.ProductGroup;
import com.example.promoevaluator.repo.CustomerRepository;
import com.example.promoevaluator.repo.ProductGroupRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PromoEvaluator {

    private final CustomerRepository customerRepository;
    private final ProductGroupRepository productGroupRepository;

    public Customer orderReceiver(Order order){
            
        log.info("Order received: {}", order);

        Customer customer = customerRepository.findById(order.getCustomerId()).get();
        
        for( OrderItem item : order.getOrderItems() ) {
            
            ProductGroup productGroup = productGroupRepository.findById(item.getProduct().getProductGroupId()).get();

            if(productGroup.getCampaigns() != null){
                for( Campaign campaign : productGroup.getCampaigns() ){

                    // update available campaign
                    if(customer.getAvailableCampaigns() != null && customer.getAvailableCampaigns().containsKey(campaign)){
                        // existing available campaign
                        Integer existingRemain = customer.getAvailableCampaigns().get(campaign);
                        customer.updateAvailableCampaign(campaign, existingRemain - item.getPrice());
                    }else{
                        // new available campaign
                        customer.updateAvailableCampaign(campaign, campaign.getProductGroupAmountMap().get(productGroup) - item.getPrice());
                    }
                    
                }
            }
        }

        return customer;

    }
}
