package com.example.event.domain;


import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.TicketType;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
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
    private final TicketInventory ticketInventory;
    @Getter
    private final LocalDateTime startDateTime;
    @Getter
    private final LocalDateTime endDateTime;
    @Getter
    private final Announcement announcement; //선택 필드

    private Event(String eventName, Venue venue, Host host, Sponsor sponsor,
        TicketInventory tickets,
        LocalDateTime startDateTime, LocalDateTime endDateTime, Announcement announcement) {
        this.eventName = eventName;
        this.venue = venue;
        this.host = host;
        this.sponsor = sponsor;
        this.ticketInventory = tickets;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.announcement = announcement;
    }

    public static Event createEvent(String eventName, Venue venue, Host host, Sponsor sponsor,
        TicketInventory ticketInventory
        , LocalDateTime startDateTime, LocalDateTime endDateTime,
        Announcement announcement) {

        int totalTicketQuantity = ticketInventory.getTotalTicketQuantity();
        validateTotalTicketNumber(totalTicketQuantity);
        validateStartDateTime(startDateTime);
        validateEventDuration(startDateTime, endDateTime);
        return new Event(eventName, venue, host, sponsor, ticketInventory, startDateTime,
            endDateTime, announcement);
    }

    public static void validateTotalTicketNumber(int totalTicketCnt) {
        if (totalTicketCnt <= 0) {
            throw new EventCreateTicketNegativeException();
        }
    }

    public static void validateStartDateTime(LocalDateTime startDateTime) {
        if (!startDateTime.isAfter(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3))) {
            throw new EventCreateStartDateException();
        }
    }

    public static void validateEventDuration(LocalDateTime startDateTime,
        LocalDateTime endDateTime) {
        if (endDateTime.isBefore(startDateTime) || endDateTime.equals(startDateTime)) {
            throw new EventCreateEndDateException();
        }
    }

    public void enrollTicketTypeAndStockOfEvent(TicketType ticketType, int stock, int price,
        LocalDateTime releaseDateTime,
        LocalDateTime deadLineDateTime) {

        this.ticketInventory.addTickets(ticketType, stock, price, releaseDateTime, deadLineDateTime);

    }

}
