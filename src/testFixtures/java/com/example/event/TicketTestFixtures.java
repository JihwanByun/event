package com.example.event;

import com.example.event.domain.Ticket;
import com.example.event.domain.TicketInventory;
import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TicketTestFixtures {

    public static final int price = 100;
    public static final int stock = 100;
    public static final TicketType typeVIP = new TicketType("VIP");
    public static final LocalDateTime releaseDateTime = LocalDateTime.now();
    public static final LocalDateTime deadLineDateTime = LocalDateTime.now().plusDays(3);


    public static TicketInventory createTicketInventory() {
        TicketInventory ticketInventory = TicketInventory.createTicketInventory();

        ticketInventory.addTickets(typeVIP, stock, price, releaseDateTime, deadLineDateTime);

        return ticketInventory;
    }

}
