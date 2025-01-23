package com.example.event.domain;


import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

public final class Event {

    @Getter
    private final String eventName;
    @Getter
    private final Venue venue;
    @Getter
    private final Host host;
    @Getter
    private final Sponsor sponsor;

    @Getter
    private final int totalTicketNumber;

    private final List<Ticket> tickets;

    @Getter
    private final LocalDateTime startDateTime;
    @Getter
    private final LocalDateTime endDateTime;
    @Getter
    private final Optional<Announcement> announcement;

    private Event(String eventName, Venue venue, Host host, Sponsor sponsor, int totalTicketNumber,
        LocalDateTime startDateTime, LocalDateTime endDateTime, Announcement announcement) {
        this.eventName = eventName;
        this.venue = venue;
        this.host = host;
        this.sponsor = sponsor;
        this.totalTicketNumber = totalTicketNumber;
        this.tickets = new ArrayList<>();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.announcement = Optional.ofNullable(announcement);
    }

    public static Event createEvent(String eventName, Venue venue, Host host, Sponsor sponsor,
        int totalTicketNumber, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        validateTotalTicketNumber(totalTicketNumber);
        validateStartDateTime(startDateTime);
        validateEventDurationTime(startDateTime, endDateTime);
        return new Event(eventName, venue, host, sponsor, totalTicketNumber, startDateTime,
            endDateTime, null);
    }

    public static void validateTotalTicketNumber(int totalTicketCnt) {
        if (totalTicketCnt <= 0) {
            throw new EventCreateTicketNegativeException();
        }
    }

    public static void validateStartDateTime(LocalDateTime startDateTime) {
        if (startDateTime.isBefore(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3))) {
            throw new EventCreateStartDateException();
        }
    }

    public static void validateEventDurationTime(LocalDateTime startDateTime,
        LocalDateTime endDateTime) {
        if (endDateTime.isBefore(startDateTime)) {
            throw new EventCreateEndDateException();
        }
    }
}
