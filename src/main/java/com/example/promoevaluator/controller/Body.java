package com.example.promoevaluator.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Body.Message is the payload of a Pub/Sub event. Please refer to the docs for
// additional information regarding Pub/Sub events.
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Body {

    private Message message;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Message {

        private String messageId;
        private String publishTime;
        private String data;

    }
}