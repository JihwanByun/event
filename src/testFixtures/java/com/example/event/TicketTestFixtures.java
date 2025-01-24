package com.example.event;

import com.example.event.domain.Ticket;
import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;

public class TicketTestFixtures {

    public static final int price = 100;
    public static final TicketType typeVIP = new TicketType("VIP");
    public static final LocalDateTime releaseDateTime = LocalDateTime.now();
    public static final LocalDateTime deadLineDateTime = LocalDateTime.now().plusDays(3);

}
