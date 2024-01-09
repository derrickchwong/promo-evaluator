package com.example.promoevaluator.controller;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.model.Campaign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// Team 1
@Slf4j
@RestController
public class MomoCampaignController {
    
    private ObjectMapper objectMapper;

    public MomoCampaignController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/campaign")
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
        Campaign campaign = null;
        if(StringUtils.isNoneEmpty(data)){
            String decodedData = new String(Base64.getDecoder().decode(data));
            log.info("decodedData: {}",decodedData);
            // use object mapper to convert decodedData to OrderEvent
            
            try {
                campaign = objectMapper.readValue(decodedData, Campaign.class);

                // process the campaign
                
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        

        return ResponseEntity.ok().build();
    }
}
