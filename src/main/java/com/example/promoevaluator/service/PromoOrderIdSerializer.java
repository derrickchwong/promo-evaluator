package com.example.promoevaluator.service;

import java.io.IOException;

import com.example.promoevaluator.model.PromoOrderId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PromoOrderIdSerializer extends StdSerializer<PromoOrderId>{

    public PromoOrderIdSerializer() {
        super(PromoOrderId.class);
    }
    

    @Override
    public void serialize(PromoOrderId id, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("promoId", id.getPromoId());
        gen.writeStringField("customerId", id.getCustomerId());
        gen.writeEndObject();
    }

}
