package com.example.promoevaluator.service;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Merchant;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.OrderItem;
import com.example.promoevaluator.model.Product;
import com.example.promoevaluator.model.ProductGroup;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PromoEvaluatorTest {

    // @Test
    // public void whenNoExistingAvailableCampaign_thenAvailableCampaignIsCreated() {

    //     // Create a customer
    //     Customer customer = Customer.builder().id("1").build();
        
    //     // Create an order
    //     Order order = Order.builder().customer(customer).build();

    //     ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
    //     Product p1 = Product.builder().id("p1").productGroup(pg1).build();

    //     pg1.setProducts(List.of(p1));

    //     Merchant merchant1 = Merchant.builder().id("merchant1").build();
        
    //     Campaign campaign1 = Campaign.builder().id("campaign1").merchant(merchant1).build();
        
    //     // pg1.setCampaigns(List.of(campaign1));
    //     campaign1.setProductGroupAmountMap(Map.of(pg1, 100));

    //     // Create an order item
    //     OrderItem item1 = OrderItem.builder().product(p1).price(10).build();

    //     // Add the order item to the order
    //     order.addOrderItem(item1);

    //     // Evaluate the order
    //     PromoEvaluator promoEvaluator = new PromoEvaluator();
    //     Customer updatedCustomer = promoEvaluator.orderReceiver(order);

    //     // Assert that the customer's available campaign has been updated
    //     assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1));
    //     assertEquals(90, updatedCustomer.getAvailableCampaigns().get(campaign1));

    // }


    // @Test
    // public void whenExistingAvailableCampaign_thenAvailableCampaignIsUpdated() {

    //     // Create a customer
    //     Customer customer = Customer.builder().id("1").build();

    //     // Create an order
    //     Order order = Order.builder().customer(customer).build();
        
    //     ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
    //     Product p1 = Product.builder().id("p1").productGroup(pg1).build();
        
    //     pg1.setProducts(List.of(p1));

    //     Merchant merchant1 = Merchant.builder().id("merchant1").build();
        
    //     Campaign campaign1 = Campaign.builder().id("campaign1").merchant(merchant1).build();
        
    //     // pg1.setCampaigns(List.of(campaign1));
    //     campaign1.setProductGroupAmountMap(Map.of(pg1, 100));

    //     // Create an order item
    //     OrderItem item1 = OrderItem.builder().product(p1).price(10).build();
        
    //     // Add the order item to the order
    //     order.addOrderItem(item1);

    //     // Update the customer's available campaign
    //     customer.updateAvailableCampaign(campaign1, 90);
   
    //     // Evaluate the order
    //     PromoEvaluator promoEvaluator = new PromoEvaluator();
    //     Customer updatedCustomer = promoEvaluator.orderReceiver(order);

    //     // Assert that the customer's available campaign has been updated
    //     assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1));
    //     assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign1));

    // }


    // @Test
    // public void whenMultipleOrderItemsInAnOrderAndEachItemBelongsToDifferentCampaign_thenAvailableCampaignIsUpdated() {

    //     // Create a customer
    //     Customer customer = Customer.builder().id("1").build();
        
    //     // Create an order
    //     Order order = Order.builder().customer(customer).build();

    //     ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
    //     Product p1 = Product.builder().id("p1").productGroup(pg1).build();
        
    //     pg1.setProducts(List.of(p1));

    //     Merchant merchant1 = Merchant.builder().id("merchant1").build();
        
    //     Campaign campaign1 = Campaign.builder().id("campaign1").merchant(merchant1).build();
        
    //     // pg1.setCampaigns(List.of(campaign1));
    //     campaign1.setProductGroupAmountMap(Map.of(pg1, 100));

    //     ProductGroup pg2 = ProductGroup.builder().id("pg2").build();
    
    //     Product p2 = Product.builder().id("p2").productGroup(pg2).build();
        
    //     pg2.setProducts(List.of(p2));

    //     Merchant merchant2 = Merchant.builder().id("merchant2").build();
        
    //     Campaign campaign2 = Campaign.builder().id("campaign2").merchant(merchant2).build();

    //     // pg2.setCampaigns(List.of(campaign2));
    //     campaign2.setProductGroupAmountMap(Map.of(pg2, 100));

    //     // Create an order item
    //     OrderItem item1 = OrderItem.builder().product(p1).price(10).build();
        
    //     // Add the order item to the order
    //     order.addOrderItem(item1);

    //     // Create an order item
    //     OrderItem item2 = OrderItem.builder().product(p2).price(10).build();
        
    //     // Add the order item to the order
    //     order.addOrderItem(item2);

    //     // Update the customer's available campaign
    //     customer.updateAvailableCampaign(campaign1, 90);
    //     customer.updateAvailableCampaign(campaign2, 90);

    //     // Evaluate the order
    //     PromoEvaluator promoEvaluator = new PromoEvaluator();
    //     Customer updatedCustomer = promoEvaluator.orderReceiver(order);

    //     // Assert that the customer's available campaign has been updated
    //     assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1));
    //     assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign1));
    //     assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign2));
    //     assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign2));

    // }

    // @Test
    // public void whenNoOrderItemBelongsToAnyCampaign_thenNoAvailableCampaignIsCreated() {

    //     // Create a customer
    //     Customer customer = Customer.builder().id("1").build();

    //     // Create an order
    //     Order order = Order.builder().customer(customer).build();

    //     ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
    //     Product p1 = Product.builder().id("p1").productGroup(pg1).build();

    //     pg1.setProducts(List.of(p1));

    //     // Create an order item
    //     OrderItem item1 = OrderItem.builder().product(p1).price(10).build();

    //     // Add the order item to the order
    //     order.addOrderItem(item1);

    //     // Evaluate the order
    //     PromoEvaluator promoEvaluator = new PromoEvaluator();
    //     Customer updatedCustomer = promoEvaluator.orderReceiver(order);

    //     // Assert that the customer's available campaign has not been updated
    //     assertNull(updatedCustomer.getAvailableCampaigns());

    // }
}
