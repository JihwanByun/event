package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.HostTestFixtures;
import com.example.event.SponsorTestFixtures;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    String EVENT_NAME = EventTestFixtures.EVENT_NAME;
    Venue EVENT_VENUE = EventTestFixtures.EVENT_VENUE;
    Sponsor SPONSOR = SponsorTestFixtures.createSponsor();
    Host HOST = HostTestFixtures.createHost();
    int TOTAL_TICKET_NUMBER = 10;
    LocalDateTime EVENT_START_DATE_TIME = EventTestFixtures.startDateTime;
    LocalDateTime EVENT_END_DATE_TIME = EventTestFixtures.endDateTime;

    @Test
    @DisplayName("판매 가능한 티켓은 0장 이하로 설정할 수 없습니다.")
    void createEventWithZeroTotalTicketNumber() {
        //given
        int totalTicketNumber = 0;

        //when & then
        Assertions.assertThatThrownBy(
                () -> Event.createEvent(EVENT_NAME, EVENT_VENUE, HOST, SPONSOR,
                    totalTicketNumber,
                    EVENT_START_DATE_TIME, EVENT_END_DATE_TIME))
            .isInstanceOf(EventCreateTicketNegativeException.class)
            .hasMessage("Number of Ticket can't zero or negative");
    }

    @Test
    @DisplayName("이벤트는 종료 시간이 시작 시간보다 빠를 수 없습니다.")
    public void addEventDuration() {

        //given
        LocalDateTime endEventDateTime = EVENT_START_DATE_TIME.minusDays(1);

        //when & then
        Assertions.assertThatThrownBy(
                () -> Event.createEvent(EVENT_NAME, EVENT_VENUE, HOST, SPONSOR, TOTAL_TICKET_NUMBER,
                    EVENT_START_DATE_TIME,
                    endEventDateTime))
            .isInstanceOf(EventCreateEndDateException.class)
            .hasMessage("Event can be hold after open time");
    }

    @Test
    @DisplayName("관리자는 현재 시각보다 3일 이후에 열리는 이벤트만 생성할 수 있습니다. ")
    public void addEventStartDateTime() {

        //given

        //when

        //then

    }
}