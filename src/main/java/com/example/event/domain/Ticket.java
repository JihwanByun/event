package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import lombok.Getter;

public class Ticket {

    private final int price;
    @Getter
    private TicketStatus status;
    private final TicketType type;
    @Getter
    private final LocalDateTime releaseDateTime;
    @Getter
    private final LocalDateTime deadLineDateTime;
    private final TransactionHistory transactionHistory;

    private Ticket(int price, TicketType type, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime, TransactionHistory transactionHistory) {
        this.price = price;
        this.type = type;
        this.status = TicketStatus.NOT_RELEASED;
        this.releaseDateTime = releaseDateTime;
        this.deadLineDateTime = deadLineDateTime;
        this.transactionHistory = transactionHistory;
    }

    public static Ticket createTicket(int price, TicketType type, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {

        return new Ticket(price, type, releaseDateTime, deadLineDateTime, new TransactionHistory());
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
