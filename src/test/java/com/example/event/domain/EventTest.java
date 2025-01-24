package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.TicketType;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EventTest {

    @Test
    @DisplayName("판매 가능한 티켓 개수가 0장 이하인 경우 예외가 발생한다.")
    void shouldThrowExceptionWhenTotalTicketNumberIsZeroOrNegative() {
        //given
        String eventName = EventTestFixtures.EVENT_NAME;
        Venue eventVenue = EventTestFixtures.eventVenue;
        Host host = EventTestFixtures.host;
        Sponsor sponsor = EventTestFixtures.sponsor;
        LocalDateTime eventStartDateTime = EventTestFixtures.startDateTime;
        LocalDateTime eventEndDateTime = EventTestFixtures.endDateTime;
        Announcement announcement = EventTestFixtures.announcement;
        //when
        int totalTicketNumber = 0;

        //then
        assertThatThrownBy(
            () -> Event.createEvent(eventName, eventVenue, host, sponsor,
                totalTicketNumber,
                eventStartDateTime, eventEndDateTime, announcement))
            .isInstanceOf(EventCreateTicketNegativeException.class)
            .hasMessage(EventCreateTicketNegativeException.MESSAGE);
    }

    @Test
    @DisplayName("이벤트는 종료 시간이 시작 시간보다 빠르면 예외가 발생한다.")
    public void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {

        //given
        String eventName = EventTestFixtures.EVENT_NAME;
        Venue eventVenue = EventTestFixtures.eventVenue;
        Host host = EventTestFixtures.host;
        Sponsor sponsor = EventTestFixtures.sponsor;
        LocalDateTime eventStartDateTime = EventTestFixtures.startDateTime;
        TicketInventory ticketInventory = EventTestFixtures.tickets;
        Announcement announcement = EventTestFixtures.announcement;

        //when
        LocalDateTime eventEndDateTime = eventStartDateTime.minusDays(1);

        //when & then
        assertThatThrownBy(
            () -> Event.createEvent(eventName, eventVenue, host, sponsor, ticketInventory,
                eventStartDateTime,
                eventEndDateTime, announcement))
            .isInstanceOf(EventCreateEndDateException.class)
            .hasMessage(EventCreateEndDateException.MESSAGE);
    }

    @Test
    @DisplayName("이벤트 시작 시간이 현재 시간 기준 3일 미만일 경우 예외가 발생한다.")
    public void addEventStartDateTime() {
        //given
        String eventName = EventTestFixtures.EVENT_NAME;
        Venue eventVenue = EventTestFixtures.eventVenue;
        Host host = EventTestFixtures.host;
        Sponsor sponsor = EventTestFixtures.sponsor;
        TicketInventory tickets = EventTestFixtures.tickets;
        Announcement announcement = EventTestFixtures.announcement;

        //when
        LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(2);
        LocalDateTime eventEndDateTime = eventStartDateTime.plusDays(2);

        //then
        assertThatThrownBy(
            () -> Event.createEvent(eventName, eventVenue, host, sponsor, tickets,
                eventStartDateTime, eventEndDateTime, announcement))
            .isInstanceOf(EventCreateStartDateException.class)
            .hasMessage(EventCreateStartDateException.MESSAGE);
    }

    @Test
    @DisplayName("이벤트 시작 시간이 현재 시간 기준 3일 이후이고, 종료 시간은 시작 시간보다 이후이며,"
        + " 판매 가능한 티켓 수는 1장 이상일 때, 이벤트를 생성할 수 있다.")
    public void createEventSuccessfully() {

        //given
        String eventName = EventTestFixtures.EVENT_NAME;
        Venue eventVenue = EventTestFixtures.eventVenue;
        Host host = EventTestFixtures.host;
        Sponsor sponsor = EventTestFixtures.sponsor;
        Announcement announcement = EventTestFixtures.announcement;

        //when
        LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(4);
        LocalDateTime eventEndDateTime = eventStartDateTime.plusDays(1);
        TicketInventory ticketInventory = new TicketInventory();
        Event event = Event.createEvent(eventName, eventVenue, host, sponsor, ticketInventory,
            eventStartDateTime,
            eventEndDateTime,
            announcement);

        //then
        assertThat(event).isNotNull();
        assertThat(event.getEventName()).isEqualTo(eventName);
        assertThat(event.getVenue()).isEqualTo(eventVenue);
        assertThat(event.getHost()).isEqualTo(host);
        assertThat(event.getSponsor()).isEqualTo(sponsor);
        assertThat(event.getTicketInventory()).isEqualTo(ticketInventory);
        assertThat(event.getStartDateTime()).isEqualTo(eventStartDateTime);
        assertThat(event.getEndDateTime()).isEqualTo(eventEndDateTime);
    }


    /*
    이벤트 티켓 생성하기
     */

    /*
    @Test
    @DisplayName("이벤트에 판매할 모든 티켓의 초기 상태는 NotReleased 이다.")
    void ticketInitStatusIsNotReleased() {
        //given
        int ticketPrice = TicketTestFixtures.price;
        TicketType ticketType = TicketTestFixtures.typeVIP;
        LocalDateTime ticketReleaseDateTime = TicketTestFixtures.releaseDateTime;
        LocalDateTime ticketDeadLineDateTime = TicketTestFixtures.deadLineDateTime;

        //when
        Ticket ticket = Ticket.createTicketNotReleased(ticketPrice, ticketType,
            ticketReleaseDateTime,
            ticketDeadLineDateTime);

        //then
        assertThat(ticket.getStatus()).isEqualTo(TicketStatus.NOT_RELEASED);
        assertThat(ticketPrice).isEqualTo(ticket.getPrice());
        assertThat(ticketType).isEqualTo(ticket.getType());
        assertThat(ticketReleaseDateTime).isEqualTo(ticket.getReleaseDateTime());
        assertThat(ticketDeadLineDateTime).isEqualTo(ticket.getDeadLineDateTime());
    }

     */
}