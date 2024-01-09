package com.example.promoevaluator.controller.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.controller.Body;
import com.example.promoevaluator.controller.Body.Message;
import com.example.promoevaluator.model.demo.Customer;
import com.example.promoevaluator.model.demo.event.OrderCancelled;
import com.example.promoevaluator.model.demo.event.OrderCreated;
import com.example.promoevaluator.model.demo.event.OrderEvent;
import com.example.promoevaluator.model.demo.event.OrderItemQuantityUpdated;
import com.example.promoevaluator.service.demo.PromoEvaluator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import org.apache.commons.lang3.StringUtils;

@RestController
@Slf4j
public class OrderEventController {

    private final PromoEvaluator promoEvaluator;
    private final ObjectMapper objectMapper;

    public OrderEventController(PromoEvaluator promoEvaluator, ObjectMapper objectMapper) {
        this.promoEvaluator = promoEvaluator;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/")
    public ResponseEntity<Customer> receiveMessage(@RequestBody Body body) {

        log.info("Received a message {}", body );
        // Get PubSub message from request body.
        Body.Message message = body.getMessage();

        if (message == null) {
            String msg = "Bad Request: invalid Pub/Sub message format";
            log.info(msg);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String data = message.getData();
        log.info("data: {}",data);
        OrderEvent orderEvent = null;
        if(StringUtils.isNoneEmpty(data)){
            String decodedData = new String(Base64.getDecoder().decode(data));
            log.info("decodedData: {}",decodedData);
            // use object mapper to convert decodedData to OrderEvent
            
            try {
                orderEvent = objectMapper.readValue(decodedData, OrderEvent.class);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        

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
