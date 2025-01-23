package com.example.event.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class Ticket {

    private final int price;

    private TicketStatus status;

    private final TicketType type;

    private final LocalDateTime releaseDateTime;

    private final LocalDateTime deadLineDateTime;
}
