package com.example.event.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Event {

    private Host host;
    private Sponsor sponsor;
    private int totalTicketCnt;
    private List<Ticket> ticket;
    private String place;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

}
