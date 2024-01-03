package com.example.promoevaluator.service;

import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.OrderItem;
import com.example.promoevaluator.model.ProductGroup;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromoEvaluator {

    public Customer orderReceiver(Order order){
            
        log.info("Order received: {}", order);

        Customer customer = order.getCustomer();
        
        for( OrderItem item : order.getOrderItems() ) {
            
            ProductGroup productGroup = item.getProduct().getProductGroup();
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
