package com.example.event.domain;


import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import java.util.Optional;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Event {

    @Getter
    private final String eventName;
    @Getter
    private final Venue venue;
    @Getter
    private final Host host;
    @Getter
    private final Sponsor sponsor;
    @Getter
    private final LocalDateTime startDateTime;
    @Getter
    private final LocalDateTime endDateTime;

    private final Announcement announcement; //선택 필드

    public Optional<Announcement> getAnnouncementOfEvent() {
        return Optional.ofNullable(announcement);
    }

    private Event(String eventName, Venue venue, Host host, Sponsor sponsor,
        LocalDateTime startDateTime, LocalDateTime endDateTime, Announcement announcement) {
        this.eventName = eventName;
        this.venue = venue;
        this.host = host;
        this.sponsor = sponsor;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.announcement = announcement;
    }

    public static Event createEvent(String eventName, Venue venue, Host host, Sponsor sponsor,
         LocalDateTime startDateTime, LocalDateTime endDateTime,
        Announcement announcement) {

        validateStartDateTime(startDateTime);
        validateEventDurationTime(startDateTime, endDateTime);

        return new Event(eventName, venue, host, sponsor, startDateTime,
            endDateTime, announcement);
    }

    public static void validateStartDateTime(LocalDateTime startDateTime) {
        if (startDateTime.isBefore(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3))) {
            throw new EventCreateStartDateException(startDateTime);
        }
    }

    public static void validateEventDurationTime(LocalDateTime startDateTime,
        LocalDateTime endDateTime) {
        if (endDateTime.isBefore(startDateTime) || endDateTime.equals(startDateTime)) {
            throw new EventCreateEndDateException(endDateTime, startDateTime);
        }
    }
}
