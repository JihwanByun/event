package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.HostTestFixtures;
import com.example.event.SponsorTestFixtures;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventTest {

    String eventName;
    Venue eventVenue;
    Sponsor sponsor;
    Host host;
    int totalTicketNumber;
    LocalDateTime eventStartDateTime;
    LocalDateTime eventEndDateTime;

    @BeforeEach
    void setUp() {
        eventName = EventTestFixtures.EVENT_NAME;
        eventVenue = EventTestFixtures.EVENT_VENUE;
        sponsor = SponsorTestFixtures.createSponsor();
        host = HostTestFixtures.createHost();
        totalTicketNumber = 10;
        eventStartDateTime = EventTestFixtures.startDateTime;
        eventEndDateTime = EventTestFixtures.endDateTime;
    }


    @Test
    @DisplayName("판매 가능한 티켓 개수가 0장 이하인 경우 예외가 발생한다.")
    void shouldThrowExceptionWhenTotalTicketNumberIsZeroOrNegative() {
        //given
        int totalTicketNumber = 0;

        //when & then
        Assertions.assertThatThrownBy(
                () -> Event.createEvent(eventName, eventVenue, host, sponsor,
                    totalTicketNumber,
                    eventStartDateTime, eventEndDateTime))
            .isInstanceOf(EventCreateTicketNegativeException.class)
            .hasMessage(EventCreateTicketNegativeException.MESSAGE);
    }

    @Test
    @DisplayName("이벤트는 종료 시간이 시작 시간보다 빠르면 예외가 발생한다.")
    public void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {

        //given
        LocalDateTime endEventDateTime = eventStartDateTime.minusDays(1);

        //when & then
        Assertions.assertThatThrownBy(
                () -> Event.createEvent(eventName, eventVenue, host, sponsor, totalTicketNumber,
                    eventStartDateTime,
                    endEventDateTime))
            .isInstanceOf(EventCreateEndDateException.class)
            .hasMessage(EventCreateEndDateException.MESSAGE);
    }

    @Test
    @DisplayName("이벤트 시작 시간이 현재 시간 기준 3일 미만일 경우 예외가 발생한다.")
    public void addEventStartDateTime() {

        //given
        LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(2);
        LocalDateTime eventEndDateTime = eventStartDateTime.plusDays(2);
        //when

        //then
        Assertions.assertThatThrownBy(
                () -> Event.createEvent(eventName, eventVenue, host, sponsor, totalTicketNumber,
                    eventStartDateTime, eventEndDateTime))
            .isInstanceOf(EventCreateStartDateException.class)
            .hasMessage(EventCreateStartDateException.MESSAGE);
    }

}