package com.example.promoevaluator.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.Order;
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
    public void messageReceiver(Order order, @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        log.info("Message arrived! Payload: " + order);
        
        Customer customer = promoEvaluator.orderReceiver(order);
    
        message.ack();
    }
}
