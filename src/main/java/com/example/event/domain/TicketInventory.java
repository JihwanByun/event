package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import lombok.Getter;

public class TicketInventory {

    private final Event event;

    @Getter
    private final Map<TicketType, List<Ticket>> tickets;

    private TicketInventory(Event event) {
        this.event = event;
        this.tickets = new HashMap<>();
    }

    public int getTotalTicketQuantity() {
        return tickets.values().stream().mapToInt(List::size).sum();
    }

    public void addTickets(TicketType ticketType, int stock, int price,
        LocalDateTime releaseDateTime, LocalDateTime deadLineDateTime) {

        validateTotalTicketNumber(stock);
        validateTicketReleasedTime(this.event.getStartDateTime(), releaseDateTime,
            deadLineDateTime);

        this.tickets.putIfAbsent(ticketType, new ArrayList<>());
        List<Ticket> newTickets = IntStream.range(0, stock)
            .mapToObj(
                value -> Ticket.createTicketNotReleased(ticketType, price, releaseDateTime,
                    deadLineDateTime))
            .toList();

        this.tickets.put(ticketType, newTickets);
    }

    public static TicketInventory createTicketInventoryOfEvent(Event event) {
        return new TicketInventory(event);
    }

    private static void validateTotalTicketNumber(int ticketStock) {
        if (ticketStock <= 0) {
            throw new TicketStockNegativeException();
        }
    }

    private static void validateTicketReleasedTime(LocalDateTime eventStartDateTime,
        LocalDateTime releasedDateTime,
        LocalDateTime deadLineDateTime) {
        if (releasedDateTime.isAfter(deadLineDateTime) || releasedDateTime.isBefore(
            eventStartDateTime.minusDays(30)) || deadLineDateTime.isAfter(
            eventStartDateTime.minusDays(10))
        ) {
            throw new TicketReleasedDateTimeException();
        }
    }
}
