package com.example.event;

import com.example.event.domain.Event;
import com.example.event.domain.Ticket;
import com.example.event.domain.TicketInventory;
import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketTestFixtures {

    private static final int price = 100;
    private static final int stock = 100;
    private static final TicketType VIP = TicketType.VIP;
    private static final Event event = EventTestFixtures.createEvent();
    private static final LocalDateTime releaseDateTime = event.getStartDateTime().minusDays(13);
    private static final LocalDateTime deadLineDateTime = releaseDateTime.plusDays(3);

    public static TicketInventory createTicketInventoryOfEvent(Event event) {

        return TicketInventory.createTicketInventoryOfEventWithSalesDuration(
                event, releaseDateTime, deadLineDateTime
        );
    }

    public static List<Ticket> addTicketsWithTypeAndQuantity(TicketType ticketType, int quantity) {
        return Stream.generate(() -> Ticket.createTicket(event, price, ticketType))
                .limit(quantity)
                .collect(Collectors.toList());
    }
}
