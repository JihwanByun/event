package com.example.event.domain.value;

import java.util.Objects;
import lombok.Getter;

public final class EventPhoto {

    @Getter
    private final String url;

    public EventPhoto(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventPhoto eventPhoto = (EventPhoto) o;
        return Objects.equals(url, eventPhoto.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
