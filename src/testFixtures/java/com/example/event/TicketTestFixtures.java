package com.example.event;

import com.example.event.domain.Ticket;
import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;

public class TicketTestFixtures {

    private static final int price = 100;
    private static final TicketType typeVIP = new TicketType("VIP");
    private static final LocalDateTime releaseDateTime = LocalDateTime.now();
    private static final LocalDateTime deadLineDateTime = LocalDateTime.now().plusDays(3);

    public static Ticket createTicket(){
        return Ticket.createTicket(price, typeVIP, releaseDateTime, deadLineDateTime);
    }


}
