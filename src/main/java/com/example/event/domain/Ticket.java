package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import com.example.event.exception.ticket.TicketPriceNegativeException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import java.time.LocalDateTime;
import lombok.Getter;

public class Ticket {

    @Getter
    private final int price;
    @Getter
    private TicketStatus status;
    @Getter
    private final TicketType type;
    @Getter
    private final LocalDateTime releaseDateTime;
    @Getter
    private final LocalDateTime deadLineDateTime;

    private LocalDateTime paymentDateTime;

    private LocalDateTime refundDateTime;

    public static Ticket createTicketNotReleased(TicketType ticketType, int price,
        LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {
        return new Ticket(ticketType, price, releaseDateTime, deadLineDateTime);
    }

    private Ticket(TicketType type, int price, LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {
        this.price = price;
        this.type = type;
        this.status = TicketStatus.NOT_RELEASED;
        this.releaseDateTime = releaseDateTime;
        this.deadLineDateTime = deadLineDateTime;
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
