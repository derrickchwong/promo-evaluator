package com.example.promoevaluator.controller;

import com.example.promoevaluator.model.event.OrderEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Body.Message is the payload of a Pub/Sub event. Please refer to the docs for
// additional information regarding Pub/Sub events.
public class Body {

    private Message message;

    public Body() {
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Message {

        private String messageId;
        private String publishTime;
        private OrderEvent data;

    }
}