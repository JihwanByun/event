package com.example.event.domain;


import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateOpenDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Event {

    private String eventName;
    private String venue;

    private Host host;
    private Sponsor sponsor;
    private Announcement announcement;

    @Getter
    private int totalTicketCnt;
    private Ticket[] tickets;

    @Getter
    private LocalDateTime startDateTime;
    @Getter
    private LocalDateTime endDateTime;

    public Event(String eventName, String venue, Host host, Sponsor sponsor, int totalTicketCnt, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.eventName = eventName;
        this.venue = venue;
        this.host = host;
        this.sponsor = sponsor;
        this.totalTicketCnt = totalTicketCnt;
        this.tickets = new Ticket[totalTicketCnt];
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static Event of(String eventName, String venue, Host host, Sponsor sponsor, int totalTicketCnt, LocalDateTime startDateTime, LocalDateTime endDateTime){

        if(totalTicketCnt <= 0){
            throw new EventCreateTicketNegativeException();
        }
        if(startDateTime.isBefore(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3))){
            throw new EventCreateOpenDateException();
        }
        if(endDateTime.isBefore(startDateTime)){
            throw new EventCreateEndDateException();
        }


        return new Event(eventName, venue, host, sponsor, totalTicketCnt,  startDateTime, endDateTime);
    }
}
