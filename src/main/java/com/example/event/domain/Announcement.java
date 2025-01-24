package com.example.event.domain;

import com.example.event.domain.value.EventPhoto;
import java.util.List;

public class Announcement {

    private final String cautionMessage;

    private final List<EventPhoto> eventPhotos;

    private final EventPhoto directionsPhoto;

    public Announcement(String cautionMessage, List<EventPhoto> eventPhotos,
        EventPhoto directionsPhoto) {
        this.cautionMessage = cautionMessage;
        this.eventPhotos = eventPhotos;
        this.directionsPhoto = directionsPhoto;
    }
}
