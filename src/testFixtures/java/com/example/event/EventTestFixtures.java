package com.example.event;

import com.example.event.domain.Announcement;
import com.example.event.domain.Event;
import com.example.event.domain.TicketInventory;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.domain.value.Host;
import java.time.LocalDateTime;

public class EventTestFixtures {

    private static final String EVENT_NAME = "아이유 콘서트";
    private static final Venue eventVenue = new Venue("서울 월드컵 경기장", 3000);
    private static final Host host = HostTestFixtures.createHost();
    private static final LocalDateTime startDateTime = LocalDateTime.now().plusDays(5);
    private static final LocalDateTime endDateTime = LocalDateTime.of(2050, 12, 12, 12, 12);
    private static final Sponsor sponsor = new Sponsor("미래재단");
    private static final Announcement announcement = AnnouncementTestFixtures.createAnnouncement();

    public static Event createEvent() {
        return Event.createEvent(EVENT_NAME, eventVenue, HostTestFixtures.createHost(),
            SponsorTestFixtures.createSponsor(), startDateTime, endDateTime,
            announcement);
    }

}

