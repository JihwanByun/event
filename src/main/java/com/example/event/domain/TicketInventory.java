package com.example.event.domain;

import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import com.example.event.exception.ticket.TicketOutOfStockException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import com.example.event.exception.ticket.TicketTypeNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class TicketInventory {

    @Getter
    private final Event event;
    private final Map<TicketType, List<Ticket>> availableTickets;
    private final Map<TicketType, List<Ticket>> soldTickets;
    @Getter
    private final LocalDateTime ticketReleaseDateTime;
    @Getter
    private final LocalDateTime ticketSaleDeadLineDateTime;

    private TicketInventory(Event event, LocalDateTime ticketReleaseDateTime,
        LocalDateTime ticketSaleDeadLineDateTime) {
        this.event = event;
        this.availableTickets = new HashMap<>();
        this.soldTickets = new HashMap<>();
        this.ticketReleaseDateTime = ticketReleaseDateTime;
        this.ticketSaleDeadLineDateTime = ticketSaleDeadLineDateTime;
    }

    public int getTotalTicketQuantity() {
        int totalTicketQuantity = availableTickets.values().stream().mapToInt(List::size).sum() +
            soldTickets.values().stream().mapToInt(List::size).sum();
        return totalTicketQuantity;
    }

    public List<Ticket> findAvailableTicketsByType(TicketType ticketType) {
        return Collections.unmodifiableList(
            availableTickets.getOrDefault(ticketType, Collections.emptyList()));
    }

    public List<Ticket> findSoldTicketsByType(TicketType ticketType) {
        return Collections.unmodifiableList(
            soldTickets.getOrDefault(ticketType, Collections.emptyList()));
    }

    public void addTickets(List<Ticket> tickets) {

        validateTotalTicketNumber(tickets.size());
        for (Ticket ticket : tickets) {
            this.availableTickets
                .computeIfAbsent(ticket.getType(), k -> new ArrayList<>())
                .add(ticket);
        }
    }

    public void buyTicketWithTypeAndQuantity(TicketType ticketType, int quantity) {

        if (!this.availableTickets.containsKey(ticketType)) {
            throw new TicketTypeNotFoundException(ticketType.getValue());
        }

        int stock = this.availableTickets.get(ticketType).size();
        if (stock < quantity) {
            throw new TicketOutOfStockException(stock);
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

    public static TicketInventory createTicketInventoryOfEventWithSalesDuration(Event event,
        LocalDateTime ticketReleaseDateTime,
        LocalDateTime ticketSaleDeadLineDateTime) {

        validateTicketReleasedTime(event.getStartDateTime(), ticketReleaseDateTime,
            ticketSaleDeadLineDateTime);
        return new TicketInventory(event, ticketReleaseDateTime, ticketSaleDeadLineDateTime);
    }

    private static void validateTicketReleasedTime(LocalDateTime eventStartDateTime,
        LocalDateTime releasedDateTime,
        LocalDateTime deadLineDateTime) {
        if (releasedDateTime.isAfter(deadLineDateTime) ||
            releasedDateTime.isBefore(eventStartDateTime.minusDays(30)) ||
            deadLineDateTime.isAfter(eventStartDateTime.minusDays(10))) {
            throw new TicketReleasedDateTimeException(releasedDateTime);
        }
    }

    private static void validateTotalTicketNumber(int totalTicketCnt) {
        if (totalTicketCnt <= 0) {
            throw new TicketStockNegativeException(totalTicketCnt);
        }
    }
}
