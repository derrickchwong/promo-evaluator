package com.example.promoevaluator.model.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = OrderCreated.class, name = "ORDER_CREATED"),
    @JsonSubTypes.Type(value = OrderCancelled.class, name = "ORDER_CANCELLED")
})
@Data
@NoArgsConstructor
public abstract class OrderEvent {
    private String type;
    private String eventId;
    private String orderId;
}
