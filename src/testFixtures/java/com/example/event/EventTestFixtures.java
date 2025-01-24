package com.example.event;

import com.example.event.domain.Announcement;
import com.example.event.domain.Event;
import com.example.event.domain.Ticket;
import com.example.event.domain.TicketInventory;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.TicketType;
import com.example.event.domain.value.Venue;
import com.example.event.domain.value.Host;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventTestFixtures {

    public static final String EVENT_NAME = "아이유 콘서트";
    public static final Venue eventVenue = new Venue("서울 월드컵 경기장", 3000);
    public static final Host host = HostTestFixtures.createHost();
    public static final LocalDateTime startDateTime = LocalDateTime.now().plusDays(5);
    public static final LocalDateTime endDateTime = LocalDateTime.of(2050, 12, 12, 12, 12);
    public static final TicketInventory tickets = new TicketInventory();
    public static final Sponsor sponsor = new Sponsor("미래재단");
    public static final Announcement announcement = AnnouncementTestFixtures.createAnnouncement();

    public static Event createEvent() {
        return Event.createEvent(EVENT_NAME, eventVenue, HostTestFixtures.createHost(),
            SponsorTestFixtures.createSponsor(), tickets, startDateTime, endDateTime,
            announcement);
    }
}

