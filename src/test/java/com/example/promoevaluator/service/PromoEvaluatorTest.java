package com.example.promoevaluator.service;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Merchant;
import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.model.OrderItem;
import com.example.promoevaluator.model.Product;
import com.example.promoevaluator.model.ProductGroup;
import com.example.promoevaluator.model.event.OrderCreated;
import com.example.promoevaluator.model.event.OrderItemQuantityUpdated;
import com.example.promoevaluator.repo.CustomerRepository;
import com.example.promoevaluator.repo.OrderRepository;
import com.example.promoevaluator.repo.ProductGroupRepository;
import com.example.promoevaluator.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PromoEvaluatorTest {


    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ProductGroupRepository productGroupRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PromoEvaluator promoEvaluator;
    
    @Test
    public void whenNoExistingAvailableCampaign_thenAvailableCampaignIsCreated() {

        // Create a customer
        Customer customer = Customer.builder().id("1").build();
        
        // Create an order
        OrderCreated order = new OrderCreated();
        order.setCustomerId(customer.getId());
        

        ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
        Product p1 = Product.builder().id("p1").productGroupId(pg1.getId()).build();

        pg1.setProducts(List.of(p1));

        Merchant merchant1 = Merchant.builder().id("merchant1").build();
        
        Campaign campaign1 = Campaign.builder().id("campaign1").merchantId(merchant1.getId()).build();
        merchant1.addCampaign(campaign1);
        
        // pg1.setCampaigns(List.of(campaign1));
        campaign1.setProductGroupAmountMap(Map.of(pg1.getId(), 100));
        pg1.addCampaign(campaign1);


        // Create an order item
        // OrderItem item1 = OrderItem.builder().productId(p1.getId()).price(10).build();
        OrderItem item1 = new OrderItem();
        item1.setProductId(p1.getId());
        item1.setPrice(10);
        

        // Add the order item to the order
        order.addOrderItem(item1);


        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(productGroupRepository.findById(pg1.getId())).thenReturn(Optional.of(pg1));
        when(productRepository.findById(p1.getId())).thenReturn(Optional.of(p1));
        when(customerRepository.save(customer)).thenReturn(customer);


        // Evaluate the order
        Customer updatedCustomer = promoEvaluator.orderReceiver(order);

        // Assert that the customer's available campaign has been updated
        assertNotNull(updatedCustomer.getAvailableCampaigns());
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1.getId()));
        assertEquals(90, updatedCustomer.getAvailableCampaigns().get(campaign1.getId()));

    }


    @Test
    public void whenExistingAvailableCampaign_thenAvailableCampaignIsUpdated() {

        // Create a customer
        Customer customer = Customer.builder().id("1").build();

        // Create an order
        OrderCreated order = new OrderCreated();
        order.setCustomerId(customer.getId());
        
        ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
        Product p1 = Product.builder().id("p1").productGroupId(pg1.getId()).build();
        
        pg1.setProducts(List.of(p1));

        Merchant merchant1 = Merchant.builder().id("merchant1").build();
        
        Campaign campaign1 = Campaign.builder().id("campaign1").merchantId(merchant1.getId()).build();

        merchant1.addCampaign(campaign1);
        
        // pg1.setCampaigns(List.of(campaign1));
        campaign1.setProductGroupAmountMap(Map.of(pg1.getId(), 100));
        pg1.addCampaign(campaign1);


        // Create an order item
        // OrderItem item1 = OrderItem.builder().productId(p1.getId()).price(10).build();
        OrderItem item1 = new OrderItem();
        item1.setProductId(p1.getId());
        item1.setPrice(10);
        
        
        // Add the order item to the order
        order.addOrderItem(item1);

        // Update the customer's available campaign
        customer.updateAvailableCampaign(campaign1, 90);
   

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(productGroupRepository.findById(pg1.getId())).thenReturn(Optional.of(pg1));
        when(productRepository.findById(p1.getId())).thenReturn(Optional.of(p1));
        when(customerRepository.save(customer)).thenReturn(customer);

        // Evaluate the order
        Customer updatedCustomer = promoEvaluator.orderReceiver(order);

        // Assert that the customer's available campaign has been updated
        assertNotNull(updatedCustomer.getAvailableCampaigns());
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1.getId()));
        assertEquals(80, updatedCustomer.getAvailableCampaigns().get(campaign1.getId()));

    }

    @Test
    public void whenMultipleOrderItemsInAnOrderAndEachItemBelongsToDifferentCampaign_thenAvailableCampaignIsUpdated() {

        // Create a customer
        Customer customer = Customer.builder().id("1").build();
        
        // Create an order
        Order order = Order.builder().customerId(customer.getId()).build();

        ProductGroup pg1 = ProductGroup.builder().id("pg1").build();
        
        Product p1 = Product.builder().id("p1").productGroupId(pg1.getId()).build();
        
        pg1.setProducts(List.of(p1));
        

        Merchant merchant1 = Merchant.builder().id("merchant1").build();
        
        Campaign campaign1 = Campaign.builder().id("campaign1").merchantId(merchant1.getId()).build();
        
        pg1.setCampaigns(List.of(campaign1));
        campaign1.setProductGroupAmountMap(Map.of(pg1.getId(), 200));

        merchant1.addCampaign(campaign1);

        ProductGroup pg2 = ProductGroup.builder().id("pg2").build();
    
        Product p2 = Product.builder().id("p2").productGroupId(pg2.getId()).build();
        
        pg2.setProducts(List.of(p2));

        Merchant merchant2 = Merchant.builder().id("merchant2").build();
        
        Campaign campaign2 = Campaign.builder().id("campaign2").merchantId(merchant2.getId()).build();

        pg2.setCampaigns(List.of(campaign2));
        campaign2.setProductGroupAmountMap(Map.of(pg2.getId(), 500));

        merchant2.addCampaign(campaign2);
        // Create an order item
        // OrderItem item1 = OrderItem.builder().productId(p1.getId()).price(10).build();
        OrderItem item1 = new OrderItem();
        item1.setProductId(p1.getId());
        item1.setPrice(10);
        
        
        // Add the order item to the order
        order.addOrderItem(item1);

        // Create an order item
        // OrderItem item2 = OrderItem.builder().productId(p2.getId()).price(20).build();
        OrderItem item2 = new OrderItem();
        item2.setProductId(p2.getId());
        item2.setPrice(20);
        
        
        // Add the order item to the order
        order.addOrderItem(item2);

        // Update the customer's available campaign
        // customer.updateAvailableCampaign(campaign1, 190);
        // customer.updateAvailableCampaign(campaign2, 480);

        OrderCreated orderCreated = new OrderCreated();
        orderCreated.setEventId(UUID.randomUUID().toString());
        orderCreated.setCustomerId(customer.getId());
        orderCreated.addOrderItem(item1);
        orderCreated.addOrderItem(item2);


        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(productGroupRepository.findById(pg1.getId())).thenReturn(Optional.of(pg1));
        when(productGroupRepository.findById(pg2.getId())).thenReturn(Optional.of(pg2));
        when(productRepository.findById(p1.getId())).thenReturn(Optional.of(p1));
        when(productRepository.findById(p2.getId())).thenReturn(Optional.of(p2));
        when(customerRepository.save(customer)).thenReturn(customer);


        // Evaluate the order
        Customer updatedCustomer = promoEvaluator.orderReceiver(orderCreated);

        // Assert that the customer's available campaign has been updated
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign1.getId()));
        assertEquals(190, updatedCustomer.getAvailableCampaigns().get(campaign1.getId()));
        assertTrue(updatedCustomer.getAvailableCampaigns().containsKey(campaign2.getId()));
        assertEquals(480, updatedCustomer.getAvailableCampaigns().get(campaign2.getId()));

    }

    @Test
    public void whenNoOrderItemBelongsToAnyCampaign_thenNoAvailableCampaignIsCreated() {
        // Create a customer
        Customer customer = Customer.builder().id("1").build();
                
        // Create an order
        Order order = Order.builder().customerId(customer.getId()).build();

        ProductGroup pg1 = ProductGroup.builder().id("pg1").build();

        Product p1 = Product.builder().id("p1").productGroupId(pg1.getId()).build();

        pg1.setProducts(List.of(p1));

        // Create an order item
        // OrderItem item1 = OrderItem.builder().productId(p1.getId()).price(10).build();
        OrderItem item1 = new OrderItem();
        item1.setProductId(p1.getId());
        item1.setPrice(10);
        

        // Add the order item to the order
        order.addOrderItem(item1);

        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));
        when(productGroupRepository.findById(pg1.getId())).thenReturn(Optional.of(pg1));
        when(productRepository.findById(p1.getId())).thenReturn(Optional.of(p1));
        when(customerRepository.save(customer)).thenReturn(customer);
        

        OrderCreated orderCreated = new OrderCreated();
        orderCreated.setEventId(UUID.randomUUID().toString());
        orderCreated.setCustomerId(customer.getId());
        orderCreated.addOrderItem(item1);

        // Evaluate the order
        Customer updatedCustomer = promoEvaluator.orderReceiver(orderCreated);

        assertTrue(updatedCustomer.getAvailableCampaigns() == null || updatedCustomer.getAvailableCampaigns().isEmpty());
    }

    @Test
    public void whenOrderItemQuantityReduced_thenAvailableCampaignRemainingAmountIsIncreasedAccordingly() {
       // Create a customer.
       Customer customer = Customer.builder().id("1").build();

       // Create an order.
       Order order = Order.builder()
           .id("1")
           .customerId(customer.getId())
           .build();

       // Create an order item.
    //    OrderItem orderItem = OrderItem.builder().productId("p1").price(10).quantity(2).build();
       OrderItem orderItem = new OrderItem();
        orderItem.setProductId("p1");
        orderItem.setPrice(10);
        orderItem.setQuantity(2);
       
       order.addOrderItem(orderItem);

       // Create a product group.
       ProductGroup productGroup = ProductGroup.builder().id("1").build();

       // Create a product
       Product product = Product.builder().id("p1").productGroupId(productGroup.getId()).build();
       
       // Create a campaign.
       Campaign campaign = Campaign.builder().id("1").merchantId("1").productGroupAmountMap(Map.of(productGroup.getId(), 100)).build();

       customer.updateAvailableCampaign(campaign, 80);

       // Add the campaign to the product group.
       productGroup.addCampaign(campaign);

       // Mock the customer repository.
       when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

       // Mock the order repository.
       when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

       // Mock the product repository.
       when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

       // Mock the product group repository.
       when(productGroupRepository.findById(productGroup.getId())).thenReturn(Optional.of(productGroup));

       // Call the orderItemQuantityUpdated method.

       OrderItemQuantityUpdated orderitemQuantityUpdated = new OrderItemQuantityUpdated();
       orderitemQuantityUpdated.setProductId(product.getId());
       orderitemQuantityUpdated.setOrderId(order.getId());
       orderitemQuantityUpdated.setNewQuantity(1);
       orderitemQuantityUpdated.setPreviousQuantity(2);
       orderitemQuantityUpdated.setEventId(UUID.randomUUID().toString());

       Customer updatedCustomer = promoEvaluator.orderItemQuantityUpdated(orderitemQuantityUpdated);

       // Verify that the customer's available campaign amount was updated.
       Integer expectedAvailableCampaignAmount = 90;
       Integer actualAvailableCampaignAmount = updatedCustomer.getAvailableCampaigns().get(campaign.getId());
       assertEquals(expectedAvailableCampaignAmount, actualAvailableCampaignAmount);
    }

    @Test
    public void whenOrderItemQuantityIncreased_thenAvailableCampaignRemainingAmountIsDecreasedAccordingly() {
        // Create a customer.
        Customer customer = Customer.builder().id("1").build();

        // Create an order.
        Order order = Order.builder()
            .id("1")
            .customerId(customer.getId())
            .build();

        // Create an order item.
        // OrderItem orderItem = OrderItem.builder().productId("p1").price(10).quantity(2).build();
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId("p1");
        orderItem.setPrice(10);
        orderItem.setQuantity(2);
        
        
        order.addOrderItem(orderItem);

        // Create a product group.
        ProductGroup productGroup = ProductGroup.builder().id("1").build();

        // Create a product
        Product product = Product.builder().id("p1").productGroupId(productGroup.getId()).build();
        
        // Create a campaign.
        Campaign campaign = Campaign.builder().id("1").merchantId("1").productGroupAmountMap(Map.of(productGroup.getId(), 100)).build();

        customer.updateAvailableCampaign(campaign, 80);

        // Add the campaign to the product group.
        productGroup.addCampaign(campaign);

        // Mock the customer repository.
        when(customerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        // Mock the order repository.
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        // Mock the product repository.
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // Mock the product group repository.
        when(productGroupRepository.findById(productGroup.getId())).thenReturn(Optional.of(productGroup));

        // Call the orderItemQuantityUpdated method.

        OrderItemQuantityUpdated orderitemQuantityUpdated = new OrderItemQuantityUpdated();
        orderitemQuantityUpdated.setProductId(product.getId());
        orderitemQuantityUpdated.setOrderId(order.getId());
        orderitemQuantityUpdated.setNewQuantity(3);
        orderitemQuantityUpdated.setPreviousQuantity(2);
        orderitemQuantityUpdated.setEventId(UUID.randomUUID().toString());

        Customer updatedCustomer = promoEvaluator.orderItemQuantityUpdated(orderitemQuantityUpdated);

        // Verify that the customer's available campaign amount was updated.
        Integer expectedAvailableCampaignAmount = 70;
        Integer actualAvailableCampaignAmount = updatedCustomer.getAvailableCampaigns().get(campaign.getId());
        assertEquals(expectedAvailableCampaignAmount, actualAvailableCampaignAmount);
    }
}
