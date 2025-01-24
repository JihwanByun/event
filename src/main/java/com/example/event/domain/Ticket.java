package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;

public class Ticket {

    private final int price;

    private TicketStatus status;

    private final TicketType type;

    private final LocalDateTime releaseDateTime;

    private final LocalDateTime deadLineDateTime;

    private LocalDateTime payDateTime;

    private LocalDateTime refundDateTime;

    private Ticket(int price, TicketType type, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {
        this.price = price;
        this.type = type;
        this.releaseDateTime = releaseDateTime;
        this.deadLineDateTime = deadLineDateTime;
    }

    public static Ticket createTicket(int price, TicketType type, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {

        return new Ticket(price, type, releaseDateTime, deadLineDateTime);
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
