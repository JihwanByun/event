package com.example.event;

import com.example.event.domain.Event;
import com.example.event.domain.TicketInventory;
import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;

public class TicketTestFixtures {

    private static final int price = 100;
    private static final int stock = 100;
    private static final TicketType typeVIP = new TicketType("VIP");
    private static final Event event = EventTestFixtures.createEvent();
    private static final LocalDateTime releaseDateTime = event.getStartDateTime().minusDays(13);
    private static final LocalDateTime deadLineDateTime = releaseDateTime.plusDays(3);

    public static TicketInventory createTicketInventoryOfEvent(
        Event event
    ) {
        TicketInventory ticketInventory = TicketInventory.createTicketInventoryOfEvent(event);
        ticketInventory.addTickets(typeVIP, stock, price, releaseDateTime, deadLineDateTime);

        return ticketInventory;
    }

}
