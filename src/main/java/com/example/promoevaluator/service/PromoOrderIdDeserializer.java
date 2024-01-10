package com.example.promoevaluator.service;

import java.io.IOException;

import com.example.promoevaluator.model.PromoOrderId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class PromoOrderIdDeserializer extends StdDeserializer<PromoOrderId>{

    public PromoOrderIdDeserializer() {
        super(PromoOrderId.class);
    }

    public PromoOrderIdDeserializer(Class<?> vc) {
        super(vc);
    }

    public PromoOrderIdDeserializer(JavaType valueType) {
        super(valueType);
    }
    
    @Override
    public PromoOrderId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        return new PromoOrderId(node.get("promoId").asText(), node.get("customerId").asText());
    }

    
}