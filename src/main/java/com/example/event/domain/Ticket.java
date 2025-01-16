package com.example.event.domain;

import java.time.LocalDateTime;

public class Ticket {

    private int price;
    private TicketStatus status;
    private String type;
    private LocalDateTime releaseDateTime;
    private LocalDateTime deadLineDateTime;
}
