package com.example.promoevaluator.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document
@Data
@EqualsAndHashCode(exclude="campaigns")
public class Merchant {
    @Id
    private String id;
    private String name;
    @DocumentReference(lazy = true)
    private List<Campaign> campaigns;
}
