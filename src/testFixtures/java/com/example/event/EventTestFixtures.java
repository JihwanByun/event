package com.example.event;

import com.example.event.domain.Event;
import com.example.event.domain.value.Venue;
import com.example.event.domain.Host;
import java.time.LocalDateTime;

public class EventTestFixtures {

    private static final String EVENT_NAME = "아이유 콘서트";
    private static final Venue EVENT_VENUE = new Venue("서울 월드컵 경기장");
    private static final Host host = HostTestFixtures.createHost();
    private static final LocalDateTime startDateTime = LocalDateTime.now().plusDays(5);
    private static final LocalDateTime endDateTime = LocalDateTime.of(2050, 12, 12, 12, 12);

    public static Event createEventWithTotalTicketNumber(int totalTicketNumber) {
        return Event.createEvent(EVENT_NAME, EVENT_VENUE, HostTestFixtures.createHost(),
            SponsorTestFixtures.createSponsor(), totalTicketNumber, startDateTime, endDateTime);
    }
}

