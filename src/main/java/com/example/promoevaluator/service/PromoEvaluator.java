package com.example.promoevaluator.service;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.OrderItem;
import com.example.promoevaluator.model.Product;
import com.example.promoevaluator.model.ProductGroup;
import com.example.promoevaluator.model.event.OrderCancelled;
import com.example.promoevaluator.model.event.OrderCreated;
import com.example.promoevaluator.model.event.OrderItemQuantityUpdated;
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

        order.getOrderItems().values().parallelStream().forEach(item -> {
            Product product = productRepository.findById(item.getProductId()).get();
            ProductGroup productGroup = productGroupRepository.findById(product.getProductGroupId()).get();

            if(productGroup.getCampaigns() != null){
                productGroup.getCampaigns().parallelStream().forEach(campaign -> {
                    Integer existingRemain = customer.getAvailableCampaigns().get(campaign.getId());
                    customer.updateAvailableCampaign(campaign, existingRemain + item.getPrice());
                });
            }
        });

        customerRepository.save(customer);
        return customer;
    }


    public Customer orderReceiver(OrderCreated orderCreatedEvent){
            
        log.info("OrderCreated received: {}", orderCreatedEvent);

        Order order = Order.builder()
            .id(orderCreatedEvent.getOrderId())
            .customerId(orderCreatedEvent.getCustomerId())
            .orderItems(
                orderCreatedEvent.getOrderItems().stream().collect(Collectors.toMap(OrderItem::getProductId, Function.identity()))
            ).build();
        
        orderRepository.save(order);
        Customer customer = customerRepository.findById(order.getCustomerId()).get();
        customer.addOrder(order);

        order.getOrderItems().values().parallelStream().forEach(item -> {
            Product product = productRepository.findById(item.getProductId()).get();
            ProductGroup productGroup = productGroupRepository.findById(product.getProductGroupId()).get();

            if(productGroup.getCampaigns() != null)
                productGroup.getCampaigns().parallelStream().forEach(
                    campaign -> {
                        if(customer.getAvailableCampaigns() != null && customer.getAvailableCampaigns().containsKey(campaign.getId())){
                            // existing available campaign
                            Integer existingRemain = customer.getAvailableCampaigns().get(campaign.getId());
                            customer.updateAvailableCampaign(campaign, existingRemain - item.getPrice());
                        }else{
                            // new available campaign
                            customer.updateAvailableCampaign(campaign, campaign.getProductGroupAmountMap().get(productGroup.getId()) - item.getPrice());
                        }
                    });
        });
            
        customerRepository.save(customer);
        return customer;

    }
    
    public Customer orderItemQuantityUpdated(OrderItemQuantityUpdated orderItemQuantityUpdatedEvent) {
        log.info("OrderItemQuantityUpdated received: {}", orderItemQuantityUpdatedEvent);
    
        Order order = orderRepository.findById(orderItemQuantityUpdatedEvent.getOrderId()).get();
        OrderItem orderItem = order.getOrderItems().get(orderItemQuantityUpdatedEvent.getProductId());
        
        Customer customer = customerRepository.findById(order.getCustomerId()).get();
    
        // Get the product and product group for the updated order item.
        Product product = productRepository.findById(orderItemQuantityUpdatedEvent.getProductId()).get();
        ProductGroup productGroup = productGroupRepository.findById(product.getProductGroupId()).get();
    
        // Update the customer's available campaigns based on the updated order item quantity.
        if (productGroup.getCampaigns() != null) {
            productGroup.getCampaigns().parallelStream().forEach(campaign -> {
                // Get the existing available campaign amount for the customer.
                Integer existingRemain = customer.getAvailableCampaigns().get(campaign.getId());
    
                // Update the available campaign amount based on the updated order item quantity.
                if (orderItemQuantityUpdatedEvent.getNewQuantity() > orderItemQuantityUpdatedEvent.getPreviousQuantity()) {
                    // The order item quantity has increased.
                    customer.updateAvailableCampaign(campaign, existingRemain - (orderItemQuantityUpdatedEvent.getNewQuantity() - orderItemQuantityUpdatedEvent.getPreviousQuantity()) * orderItem.getPrice());
                } else {
                    // The order item quantity has decreased.
                    customer.updateAvailableCampaign(campaign, existingRemain + (orderItemQuantityUpdatedEvent.getPreviousQuantity() - orderItemQuantityUpdatedEvent.getNewQuantity()) * orderItem.getPrice());
                }
            });
        }
    
        // Save the updated customer.
        customerRepository.save(customer);
    
        return customer;
    }
    
}
