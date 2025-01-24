package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketInventory {

    private final Map<TicketType, List<Ticket>> tickets = new HashMap<>();

    public int getTotalTicketQuantity() {
        return tickets.values().stream().mapToInt(List::size).sum();
    }

    public void addTickets(TicketType ticketType, int stock, int price,
        LocalDateTime releaseDateTime, LocalDateTime deadLineDateTime) {

    }
}
