package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket {

    private final int price;
    private TicketStatus status;
    private final TicketType type;
    private final LocalDateTime releaseDateTime;
    private final LocalDateTime deadLineDateTime;
    private final TransactionHistory transactionHistory;

    private Ticket(int price, TicketType type, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime, TransactionHistory transactionHistory) {
        this.price = price;
        this.type = type;
        this.releaseDateTime = releaseDateTime;
        this.deadLineDateTime = deadLineDateTime;
        this.transactionHistory = transactionHistory;
    }

    public static Ticket createTicket(int price, TicketType type, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {

        return new Ticket(price, type, releaseDateTime, deadLineDateTime, new TransactionHistory(new ArrayList<>()));
    }

    public void setTicketStatusForSale(Ticket ticket) {
        ticket.status = TicketStatus.FOR_SALE;
    }

    public void setTicketStatusNotReleased(Ticket ticket) {
        ticket.status = TicketStatus.NOT_RELEASED;
    }

    public void setTicketStatusSold(Ticket ticket) {
        ticket.status = TicketStatus.SOLD;
    }
}
