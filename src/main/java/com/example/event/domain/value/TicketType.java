package com.example.event.domain.value;


import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TicketType {

    private final String ticketType;

    public TicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
