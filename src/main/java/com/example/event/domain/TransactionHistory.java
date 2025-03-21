package com.example.event.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {

    private final List<TransactionRecord> records;

    public TransactionHistory() {
        this.records = new ArrayList<>();
    }

    public void addPurchaseTicketOrder() {
        this.records.add(new TransactionRecord(TransactionType.PURCHASE, LocalDateTime.now()));
    }

    public void addRefundTicketOrder() {
        this.records.add(new TransactionRecord(TransactionType.REFUND, LocalDateTime.now()));
    }
}
