package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class TicketInventory {

    private final Map<TicketType, List<Ticket>> tickets;

    private TicketInventory() {
        this.tickets = new HashMap<>();
    }

    public int getTotalTicketQuantity() {
        return tickets.values().stream().mapToInt(List::size).sum();
    }

    public void addTickets(TicketType ticketType, int stock, int price,
        LocalDateTime releaseDateTime, LocalDateTime deadLineDateTime) {
        this.tickets.putIfAbsent(ticketType, new ArrayList<>());
        List<Ticket> newTickets = IntStream.range(0, stock)
            .mapToObj(
                value -> Ticket.createTicketNotReleased(ticketType, price, releaseDateTime,
                    deadLineDateTime))
            .toList();

        this.tickets.put(ticketType, newTickets);
    }

    public static TicketInventory createTicketInventory() {
        return new TicketInventory();
    }
}
