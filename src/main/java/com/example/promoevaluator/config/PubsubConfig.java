package com.example.promoevaluator.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.example.promoevaluator.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;

@Configuration
public class PubsubConfig {

    @Bean
    public MessageChannel orderChannel() {
        return new DirectChannel();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      return mapper;
    }
    
    @Bean
    @Primary
    public PubSubMessageConverter pubSubMessageConverter(ObjectMapper objectMapper) {
       return new JacksonPubSubMessageConverter(objectMapper);
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(PubSubMessageConverter pubSubMessageConverter, 
            @Qualifier("orderChannel") MessageChannel inputChannel, 
            PubSubTemplate pubSubTemplate) {

        pubSubTemplate.setMessageConverter(pubSubMessageConverter);
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "promo-evaluator");
        adapter.setOutputChannel(inputChannel);
        adapter.setPayloadType(Order.class);
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }
}
