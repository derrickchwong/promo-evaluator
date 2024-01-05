package com.example.promoevaluator.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.event.OrderCancelled;
import com.example.promoevaluator.model.event.OrderCreated;
import com.example.promoevaluator.model.event.OrderEvent;
import com.example.promoevaluator.model.event.OrderItemQuantityUpdated;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MessageReceiver {
    

    private final PromoEvaluator promoEvaluator;

    public MessageReceiver(PromoEvaluator promoEvaluator) {
        this.promoEvaluator = promoEvaluator;
    }

    @ServiceActivator(inputChannel = "orderChannel")
    public void messageReceiver(OrderEvent orderEvent, @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        log.info("Message arrived! Payload: " + orderEvent);
       
        if(orderEvent instanceof OrderCreated){
            OrderCreated orderCreated = (OrderCreated) orderEvent;
            Customer customer = promoEvaluator.orderReceiver(orderCreated);
        }else if(orderEvent instanceof OrderCancelled){
            OrderCancelled orderCancelled = (OrderCancelled) orderEvent;
            Customer customer = promoEvaluator.orderCancelled(orderCancelled);
        }else if (orderEvent instanceof OrderItemQuantityUpdated){
            OrderItemQuantityUpdated orderItemQuantityUpdated = (OrderItemQuantityUpdated) orderEvent;
            Customer customer = promoEvaluator.orderItemQuantityUpdated(orderItemQuantityUpdated);
        }
        
        message.ack();
    }

    @ServiceActivator(inputChannel = "errorChannel")
    public void errorhandler(String payload, @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        log.info("Message arrived in error handler! Payload: " + payload);
        
        message.ack();
    }
}
