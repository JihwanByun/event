package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.HostTestFixtures;
import com.example.event.SponsorTestFixtures;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    @DisplayName("판매 가능한 티켓은 0장 이하로 설정할 수 없습니다.")
    void createEventWithZeroTotalTicketNumber() {
        //given
        int totalTicketNumber = 0;

        //when & then
        Assertions.assertThatThrownBy(
                () -> Event.createEvent(EventTestFixtures.EVENT_NAME,
                    EventTestFixtures.EVENT_VENUE,
                    HostTestFixtures.createHost(),
                    SponsorTestFixtures.createSponsor(),
                    totalTicketNumber,
                    EventTestFixtures.startDateTime,
                    EventTestFixtures.endDateTime
                ))
            .isInstanceOf(EventCreateTicketNegativeException.class)
            .hasMessage("Number of Ticket can't zero or negative");
    }

    @Test
    @DisplayName("이벤트는 종료 시간이 시작 시간보다 빠를 수 없습니다.")
    public void addEventDuration() {

        //given

        //when

        //then

    }

    @Test
    @DisplayName("관리자는 현재 시간보다 3일 이상 남은 이벤트에 대해서만 새로운 이벤트를 생성할 수 있다.")
    public void addEventStartDateTime() {

        //given

        //when

        //then

    }
}