package com.example.event.domain;

import java.time.LocalDateTime;

public class TransactionRecord {

    private final TransactionType transactionType;
    private final LocalDateTime orderDateTime;

    public TransactionRecord(TransactionType transactionType, LocalDateTime orderDateTime) {
        this.transactionType = transactionType;
        this.orderDateTime = orderDateTime;
    }
}
