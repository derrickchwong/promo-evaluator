package com.example.promoevaluator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.model.Customer;
import com.example.promoevaluator.model.event.OrderCancelled;
import com.example.promoevaluator.model.event.OrderCreated;
import com.example.promoevaluator.model.event.OrderEvent;
import com.example.promoevaluator.model.event.OrderItemQuantityUpdated;
import com.example.promoevaluator.service.PromoEvaluator;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderEventController {

    private final PromoEvaluator promoEvaluator;

    public OrderEventController(PromoEvaluator promoEvaluator) {
        this.promoEvaluator = promoEvaluator;
    }

    @PostMapping("/")
    public ResponseEntity<Customer> receiveMessage(@RequestBody Body body) {

        // Get PubSub message from request body.
        Body.Message message = body.getMessage();

        if (message == null) {
            String msg = "Bad Request: invalid Pub/Sub message format";
            log.info(msg);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        OrderEvent orderEvent = message.getData();

        Customer customer = null;
        if (orderEvent instanceof OrderCreated) {
            OrderCreated orderCreated = (OrderCreated) orderEvent;
            customer = promoEvaluator.orderReceiver(orderCreated);
        } else if (orderEvent instanceof OrderCancelled) {
            OrderCancelled orderCancelled = (OrderCancelled) orderEvent;
            customer = promoEvaluator.orderCancelled(orderCancelled);
        } else if (orderEvent instanceof OrderItemQuantityUpdated) {
            OrderItemQuantityUpdated orderItemQuantityUpdated = (OrderItemQuantityUpdated) orderEvent;
            customer = promoEvaluator.orderItemQuantityUpdated(orderItemQuantityUpdated);
        }

        return ResponseEntity.ok(customer);
    }
}
