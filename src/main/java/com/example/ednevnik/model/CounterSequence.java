package com.example.ednevnik.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "counterSequence")
public class CounterSequence {

    public static final String OUTGOING_TRANSACTION_RESPONSE_ID = "outgoingTransactionResponseId";

    @Id
    private String id;

    private Long value;
}