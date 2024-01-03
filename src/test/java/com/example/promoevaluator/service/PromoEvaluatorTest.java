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

    @Test
    public void whenNoExistingAvailableCampaign_thenAvailableCampaignIsCreated() {

        // Create a customer
        Customer customer = new Customer();
        customer.setId("1");

        // Create an order
        Order order = new Order();
        order.setCustomer(customer);

        ProductGroup pg1 = new ProductGroup();
        pg1.setId("pg1");

        Product p1 = new Product();
        p1.setId("p1");
        p1.setProductGroup(pg1);

        pg1.setProducts(List.of(p1));

        Merchant merchant1 = new Merchant();
        merchant1.setId("merchant1");        
        Campaign campaign1 = new Campaign();
        campaign1.setId("campaign1");
        campaign1.setMerchant(merchant1);
        pg1.setCampaigns(List.of(campaign1));
        campaign1.setProductGroupAmountMap(Map.of(pg1, 100));

        // Create an order item
        OrderItem item1 = new OrderItem();
        item1.setProduct(p1);
        item1.setPrice(10);

        // Add the order item to the order
        order.addOrderItem(item1);

        // Evaluate the order
        PromoEvaluator promoEvaluator = new PromoEvaluator();
        Customer updatedCustomer = promoEvaluator.orderReceiver(order);

        // Assert that the customer's available campaign has been updated
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1));
        assertEquals(90, updatedCustomer.getAvailableCampaigns().get(campaign1));

    }


    @Test
    public void whenExistingAvailableCampaign_thenAvailableCampaignIsUpdated() {

        // Create a customer
        Customer customer = new Customer();
        customer.setId("1");

        // Create an order
        Order order = new Order();
        order.setCustomer(customer);

        ProductGroup pg1 = new ProductGroup();
        pg1.setId("pg1");

        Product p1 = new Product();
        p1.setId("p1");
        p1.setProductGroup(pg1);

        pg1.setProducts(List.of(p1));

        Merchant merchant1 = new Merchant();
        merchant1.setId("merchant1");        
        Campaign campaign1 = new Campaign();
        campaign1.setId("campaign1");
        campaign1.setMerchant(merchant1);
        pg1.setCampaigns(List.of(campaign1));
        campaign1.setProductGroupAmountMap(Map.of(pg1, 100));

        // Create an order item
        OrderItem item1 = new OrderItem();
        item1.setProduct(p1);
        item1.setPrice(10);

        // Add the order item to the order
        order.addOrderItem(item1);

        // Update the customer's available campaign
        customer.updateAvailableCampaign(campaign1, 90);
   
        // Evaluate the order
        PromoEvaluator promoEvaluator = new PromoEvaluator();
        Customer updatedCustomer = promoEvaluator.orderReceiver(order);

        // Assert that the customer's available campaign has been updated
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1));
        assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign1));

    }


    @Test
    public void whenMultipleOrderItemsInAnOrderAndEachItemBelongsToDifferentCampaign_thenAvailableCampaignIsUpdated() {

        // Create a customer
        Customer customer = new Customer();
        customer.setId("1");

        // Create an order
        Order order = new Order();
        order.setCustomer(customer);

        ProductGroup pg1 = new ProductGroup();
        pg1.setId("pg1");

        Product p1 = new Product();
        p1.setId("p1");
        p1.setProductGroup(pg1);

        pg1.setProducts(List.of(p1));

        Merchant merchant1 = new Merchant();
        merchant1.setId("merchant1");        
        Campaign campaign1 = new Campaign();
        campaign1.setId("campaign1");
        campaign1.setMerchant(merchant1);
        pg1.setCampaigns(List.of(campaign1));
        campaign1.setProductGroupAmountMap(Map.of(pg1, 100));

        ProductGroup pg2 = new ProductGroup();
        pg2.setId("pg2");

        Product p2 = new Product();
        p2.setId("p2");
        p2.setProductGroup(pg2);

        pg2.setProducts(List.of(p2));

        Merchant merchant2 = new Merchant();
        merchant2.setId("merchant2");        
        Campaign campaign2 = new Campaign();
        campaign2.setId("campaign2");
        campaign2.setMerchant(merchant2);
        pg2.setCampaigns(List.of(campaign2));
        campaign2.setProductGroupAmountMap(Map.of(pg2, 100));

        // Create an order item
        OrderItem item1 = new OrderItem();
        item1.setProduct(p1);
        item1.setPrice(10);

        // Add the order item to the order
        order.addOrderItem(item1);

        // Create an order item
        OrderItem item2 = new OrderItem();
        item2.setProduct(p2);
        item2.setPrice(10);

        // Add the order item to the order
        order.addOrderItem(item2);

        // Update the customer's available campaign
        customer.updateAvailableCampaign(campaign1, 90);
        customer.updateAvailableCampaign(campaign2, 90);

        // Evaluate the order
        PromoEvaluator promoEvaluator = new PromoEvaluator();
        Customer updatedCustomer = promoEvaluator.orderReceiver(order);

        // Assert that the customer's available campaign has been updated
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1));
        assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign1));
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign2));
        assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign2));

    }

    @Test
    public void whenNoOrderItemBelongsToAnyCampaign_thenNoAvailableCampaignIsCreated() {

        // Create a customer
        Customer customer = new Customer();
        customer.setId("1");

        // Create an order
        Order order = new Order();
        order.setCustomer(customer);

        ProductGroup pg1 = new ProductGroup();
        pg1.setId("pg1");

        Product p1 = new Product();
        p1.setId("p1");
        p1.setProductGroup(pg1);

        pg1.setProducts(List.of(p1));

        // Create an order item
        OrderItem item1 = new OrderItem();
        item1.setProduct(p1);
        item1.setPrice(10);

        // Add the order item to the order
        order.addOrderItem(item1);

        // Evaluate the order
        PromoEvaluator promoEvaluator = new PromoEvaluator();
        Customer updatedCustomer = promoEvaluator.orderReceiver(order);

        // Assert that the customer's available campaign has not been updated
        assertNull(updatedCustomer.getAvailableCampaigns());

    }
}
