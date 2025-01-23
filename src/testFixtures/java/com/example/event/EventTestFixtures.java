package com.example.event;

import com.example.event.domain.Event;
import com.example.event.domain.value.Venue;
import com.example.event.domain.value.Host;
import java.time.LocalDateTime;

public class EventTestFixtures {

    public static final String EVENT_NAME = "아이유 콘서트";
    public static final Venue EVENT_VENUE = new Venue("서울 월드컵 경기장");
    public static final Host host = HostTestFixtures.createHost();
    public static final LocalDateTime startDateTime = LocalDateTime.now().plusDays(5);
    public static final LocalDateTime endDateTime = LocalDateTime.of(2050, 12, 12, 12, 12);
    public static final int totalTicketNumber = 100;

    public static Event createEvent() {
        return Event.createEvent(EVENT_NAME, EVENT_VENUE, HostTestFixtures.createHost(),
            SponsorTestFixtures.createSponsor(), totalTicketNumber, startDateTime, endDateTime);
    }
}

