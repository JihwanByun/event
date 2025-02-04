package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import com.example.event.exception.ticket.TicketOutOfStockException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import com.example.event.exception.ticket.TicketTypeNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;

public class TicketInventory {

    private final Event event;
    @Getter
    private final Map<TicketType, List<Ticket>> availableTickets;
    @Getter
    private final Map<TicketType, List<Ticket>> soldTickets;


    private TicketInventory(Event event) {
        this.event = event;
        this.availableTickets = new HashMap<>();
        this.soldTickets = new HashMap<>();
    }

    public int getTotalTicketQuantity() {
        return availableTickets.values().stream().mapToInt(List::size).sum();
    }

    public void addTickets(TicketType ticketType, int stock, int price,
        LocalDateTime releaseDateTime, LocalDateTime deadLineDateTime) {

        validateTotalTicketNumber(stock);
        validateTicketReleasedTime(this.event.getStartDateTime(), releaseDateTime,
            deadLineDateTime);

        this.availableTickets.putIfAbsent(ticketType, new LinkedList<>());
        List<Ticket> newTickets = IntStream.range(0, stock)
            .mapToObj(
                value -> Ticket.createTicket(price, ticketType, releaseDateTime,
                    deadLineDateTime))
            .collect(Collectors.toCollection(ArrayList::new));

        this.availableTickets.put(ticketType, newTickets);
    }

    public void buyTicketWithType(int quantity, TicketType ticketType) {

        if (!this.availableTickets.containsKey(ticketType)) {
            throw new TicketTypeNotFoundException();
        }
        if (this.availableTickets.get(ticketType).size() < quantity) {
            throw new TicketOutOfStockException();
        }

        List<Ticket> purchasedTickets = availableTickets.get(ticketType)
            .stream()
            .limit(quantity)
            .peek(Ticket::setTicketStatusSold)
            .toList();

        this.soldTickets.computeIfAbsent(ticketType, k -> new ArrayList<>())
            .addAll(purchasedTickets);

        purchasedTickets.forEach(
            purchaserdTicket -> this.availableTickets.get(ticketType)
                .removeIf(availableTicket -> availableTicket == purchaserdTicket));
    }

    public static TicketInventory createTicketInventoryOfEvent(Event event) {
        return new TicketInventory(event);
    }

    public static void validateTotalTicketNumber(int totalTicketCnt) {
        if (totalTicketCnt <= 0) {
            throw new TicketStockNegativeException(totalTicketCnt);
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
