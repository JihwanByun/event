package com.example.event;

import com.example.event.domain.value.Venue;

public class VenueTestFixtures {

    public static Venue createVenue(){
        return new Venue("서울 월드컵 경기장", 100);
    }
}
