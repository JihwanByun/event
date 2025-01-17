package com.example.event.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Ticket {

    private int price;
    private TicketStatus status;
    private List<TicketType> type;
    private LocalDateTime releaseDateTime;
    private LocalDateTime deadLineDateTime;
}
