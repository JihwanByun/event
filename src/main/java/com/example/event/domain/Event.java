package com.example.event.domain;


import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateOpenDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Event {

    private final String eventName;

    private final String venue;

    private final Host host;

    private final Sponsor sponsor;

    private final Announcement announcement = null;

    @Getter
    private final int totalTicketNumber;

    private final List<Ticket> tickets;

    @Getter
    private final LocalDateTime startDateTime;
    @Getter
    private final LocalDateTime endDateTime;

    private Event(String eventName, String venue, Host host, Sponsor sponsor, int totalTicketNumber,
        LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.eventName = eventName;
        this.venue = venue;
        this.host = host;
        this.sponsor = sponsor;
        this.totalTicketNumber = totalTicketNumber;
        this.tickets = new ArrayList<>();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static Event createEvent(String eventName, String venue, Host host, Sponsor sponsor,
        int totalTicketNumber, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        validateTotalTicketNumber(totalTicketNumber);
        validateStartDateTime(startDateTime);
        validateEventDurationTime(startDateTime, endDateTime);
        return new Event(eventName, venue, host, sponsor, totalTicketNumber, startDateTime,
            endDateTime);
    }

    public static void validateTotalTicketNumber(int totalTicketCnt) {
        if (totalTicketCnt <= 0) {
            throw new EventCreateTicketNegativeException();
        }
    }

    public static void validateStartDateTime(LocalDateTime startDateTime) {
        if (startDateTime.isBefore(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3))) {
            throw new EventCreateOpenDateException();
        }
    }

    public static void validateEventDurationTime(LocalDateTime startDateTime,
        LocalDateTime endDateTime) {
        if (endDateTime.isBefore(startDateTime)) {
            throw new EventCreateEndDateException();
        }
    }
}
