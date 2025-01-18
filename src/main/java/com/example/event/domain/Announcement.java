package com.example.event.domain;

import com.example.event.domain.value.CautionMessage;
import com.example.event.domain.value.EventPhoto;
import java.util.List;

public final class Announcement {

    private final CautionMessage caution;

    private final List<EventPhoto> eventPhotos;

    private final EventPhoto directionsPhoto;

    public Announcement(CautionMessage caution, List<EventPhoto> eventPhotos,
        EventPhoto directionsPhoto) {
        this.caution = caution;
        this.eventPhotos = eventPhotos;
        this.directionsPhoto = directionsPhoto;
    }
}
