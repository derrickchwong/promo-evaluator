package com.example.promoevaluator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.model.Order;
import com.example.promoevaluator.service.MomoPromoCalculator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;


// Team 1
@Slf4j
@RestController
public class MomoOrderController {
    
    private MomoPromoCalculator momoPromoCalculator;
    private ObjectMapper objectMapper;

    public MomoOrderController(MomoPromoCalculator momoPromoCalculator, ObjectMapper objectMapper) {
        this.momoPromoCalculator = momoPromoCalculator;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/order")
    public ResponseEntity receiveMessage(@RequestBody Body body) {

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
        Order order = null;
        if(StringUtils.isNoneEmpty(data)){
            String decodedData = new String(Base64.getDecoder().decode(data));
            log.info("decodedData: {}",decodedData);
            // use object mapper to convert decodedData to OrderEvent
            
            try {
                order = objectMapper.readValue(decodedData, Order.class);

                momoPromoCalculator.calculate(order);
                
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        

        return ResponseEntity.ok().build();
    }
}
