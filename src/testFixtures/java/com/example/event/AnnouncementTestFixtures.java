package com.example.event;

import com.example.event.domain.Announcement;
import com.example.event.domain.value.EventPhoto;
import java.util.ArrayList;

public class AnnouncementTestFixtures {

    public static Announcement createAnnouncement() {
        return new Announcement("주의사항", new ArrayList<>(), new EventPhoto("오시는길.jpg"));
    }
}
