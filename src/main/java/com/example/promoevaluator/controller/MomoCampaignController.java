package com.example.promoevaluator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.promoevaluator.model.Campaign;
import com.example.promoevaluator.service.PubsubMessageDecoder;
import com.example.promoevaluator.service.PubsubMessageDecoder.UnableToDecodeException;
import lombok.extern.slf4j.Slf4j;

// Team 1
@Slf4j
@RestController
public class MomoCampaignController {
    
    private PubsubMessageDecoder pubsubMessageDecoder;

    public MomoCampaignController(PubsubMessageDecoder pubsubMessageDecoder) {
        this.pubsubMessageDecoder = pubsubMessageDecoder;
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

        try {
            Campaign campaign = pubsubMessageDecoder.decode(body, Campaign.class);
        } catch (UnableToDecodeException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().build();
    }
}
