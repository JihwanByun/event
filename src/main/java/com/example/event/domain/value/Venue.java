package com.example.event.domain.value;

import java.util.Objects;

public class Venue {

    private final String name;
    private final int seatingCapacity;

    public Venue(String venue, int seatingCapacity) {
        this.name = venue;
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Venue that = (Venue) o;

        return Objects.equals(that.name, name) && Objects.equals(that.seatingCapacity,
            seatingCapacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
