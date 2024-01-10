package com.example.promoevaluator.service;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

import com.example.promoevaluator.controller.Body;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PubsubMessageDecoder {
    
    private ObjectMapper objectMapper;
    
    public PubsubMessageDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    

    public <T> T decode(Body body, Class<T> class1) throws UnableToDecodeException{
        
        // Get PubSub message from request body.
        Body.Message message = body.getMessage();

        if (message == null) {
            String msg = "Bad Request: invalid Pub/Sub message format";
            log.info(msg);
            throw new UnableToDecodeException(msg);
        }

        String data = message.getData();
        log.info("data: {}",data);
        
        if(StringUtils.isNoneEmpty(data)){
            String decodedData = new String(Base64.getDecoder().decode(data));
            log.info("decodedData: {}",decodedData);
            // use object mapper to convert decodedData to OrderEvent
            
            try {
                T t = objectMapper.readValue(decodedData, class1);

                return t;
                
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                throw new UnableToDecodeException(e.getMessage());
            }
        }
        return null;
        
    }

    public class UnableToDecodeException extends Exception {
        private static final long serialVersionUID = 1L;

        public UnableToDecodeException(String message) {
            super(message);
        }
    }

}
