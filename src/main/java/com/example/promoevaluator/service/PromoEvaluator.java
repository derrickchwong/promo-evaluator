package com.example.promoevaluator.service;

import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.OrderItem;
import com.example.promoevaluator.model.Product;
import com.example.promoevaluator.model.ProductGroup;
import com.example.promoevaluator.model.event.OrderCancelled;
import com.example.promoevaluator.model.event.OrderCreated;
import com.example.promoevaluator.repo.CustomerRepository;
import com.example.promoevaluator.repo.OrderRepository;
import com.example.promoevaluator.repo.ProductGroupRepository;
import com.example.promoevaluator.repo.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PromoEvaluator {

    private final CustomerRepository customerRepository;
    private final ProductGroupRepository productGroupRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public Customer orderCancelled(OrderCancelled orderCancelledEvent){
        log.info("OrderCancelled received: {}", orderCancelledEvent);

        Order order = orderRepository.findById(orderCancelledEvent.getOrderId()).get();
        Customer customer = customerRepository.findById(order.getCustomerId()).get();
        customer.cancelOrder(order);

        for( OrderItem item : order.getOrderItems() ) {
            
            Product product = productRepository.findById(item.getProductId()).get();
            ProductGroup productGroup = productGroupRepository.findById(product.getProductGroupId()).get();

            if(productGroup.getCampaigns() != null){
                for( Campaign campaign : productGroup.getCampaigns() ){
    
                    Integer existingRemain = customer.getAvailableCampaigns().get(campaign.getId());
                    customer.updateAvailableCampaign(campaign, existingRemain + item.getPrice());
                
                }
            }
        }
        customerRepository.save(customer);
        return customer;
    }


    public Customer orderReceiver(OrderCreated orderCreatedEvent){
            
        log.info("OrderCreated received: {}", orderCreatedEvent);

        Order order = Order.builder()
            .id(orderCreatedEvent.getOrderId())
            .customerId(orderCreatedEvent.getCustomerId())
            .orderItems(orderCreatedEvent.getOrderItems())
            .build();
        
        orderRepository.save(order);
        Customer customer = customerRepository.findById(order.getCustomerId()).get();
        customer.addOrder(order);
        

        for( OrderItem item : order.getOrderItems() ) {
            
            Product product = productRepository.findById(item.getProductId()).get();
            ProductGroup productGroup = productGroupRepository.findById(product.getProductGroupId()).get();

            if(productGroup.getCampaigns() != null){
                for( Campaign campaign : productGroup.getCampaigns() ){

                    // update available campaign
                    if(customer.getAvailableCampaigns() != null && customer.getAvailableCampaigns().containsKey(campaign.getId())){
                        // existing available campaign
                        Integer existingRemain = customer.getAvailableCampaigns().get(campaign.getId());
                        customer.updateAvailableCampaign(campaign, existingRemain - item.getPrice());
                    }else{
                        // new available campaign
                        customer.updateAvailableCampaign(campaign, campaign.getProductGroupAmountMap().get(productGroup.getId()) - item.getPrice());
                    }
                    
                }
            }
        }
        customerRepository.save(customer);
        return customer;

    }
}
