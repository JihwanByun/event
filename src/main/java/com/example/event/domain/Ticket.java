package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import lombok.Getter;

public class Ticket {

    private final Event event;
    private final int price;
    @Getter
    private TicketStatus status;
    @Getter
    private final TicketType type;

    private final TransactionHistory transactionHistory;

    private Ticket(Event event, int price, TicketType type, TransactionHistory transactionHistory) {
        this.event = event;
        this.price = price;
        this.type = type;
        this.status = TicketStatus.NOT_RELEASED;
        this.transactionHistory = transactionHistory;
    }

    public static Ticket createTicket(Event event, int price, TicketType type) {

        return new Ticket(event, price, type, new TransactionHistory());
    }

    public void setTicketStatusForSale() {
        this.status = TicketStatus.FOR_SALE;
    }

    public void setTicketStatusNotReleased() {
        this.status = TicketStatus.NOT_RELEASED;
    }

    public void setTicketStatusSold() {
        this.status = TicketStatus.SOLD;
    }
}
