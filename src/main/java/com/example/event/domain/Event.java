package com.example.event.domain;


import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.TicketType;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import com.example.event.exception.ticket.TicketPriceNegativeException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.lang.Nullable;

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
    private final Announcement announcement; //선택 필드

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
        this.announcement = announcement;
    }

    public static Event createEvent(String eventName, Venue venue, Host host, Sponsor sponsor,
        int totalTicketNumber, LocalDateTime startDateTime, LocalDateTime endDateTime,
        Announcement announcement) {
        if (!isValidTotalTicketNumber(totalTicketNumber)) {
            throw new EventCreateTicketNegativeException();
        }
        if (!isValidEventStartDateTime(startDateTime)) {
            throw new EventCreateStartDateException();
        }
        if (!isValidEventDuration(startDateTime, endDateTime)) {
            throw new EventCreateEndDateException();
        }
        return new Event(eventName, venue, host, sponsor, totalTicketNumber, startDateTime,
            endDateTime, announcement);
    }

    public static boolean isValidTotalTicketNumber(int totalTicketCnt) {
        return totalTicketCnt > 0;
    }

    public static boolean isValidEventStartDateTime(LocalDateTime startDateTime) {
        return startDateTime.isBefore(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3));
    }

    public static boolean isValidEventDuration(LocalDateTime startDateTime,
        LocalDateTime endDateTime) {
        return endDateTime.isBefore(startDateTime);
    }

    public static boolean isValidPrice(int price) {
        return price > 0;
    }
}
