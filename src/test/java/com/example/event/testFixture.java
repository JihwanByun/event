package com.example.event;

import com.example.event.domain.Event;
import com.example.event.domain.Host;
import com.example.event.domain.Sponsor;

import java.time.LocalDateTime;

public class testFixture {

    public static Host createHost(){
        return new Host("떡잎호스트", "김담당자", "host@mail.com", "010-0000-0000");
    }

    public static Sponsor createSponsor(){
        return new Sponsor("A스폰서");
    }

    public static Event createEventWithTotalTicketAndStartTimeAndEndTime(int totalTicket, LocalDateTime startEventDateTime, LocalDateTime endEventDateTime){
        return Event.of("짱구 콘서트", "올림픽 경기장", createHost(), createSponsor(), totalTicket, startEventDateTime, endEventDateTime );
    }
}
