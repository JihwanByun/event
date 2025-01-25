package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.TicketType;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateTicketNegativeException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TicketTest {

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
        TicketInventory ticketInventory = TicketInventory.createTicketInventory();
        int ticketPrice = TicketTestFixtures.price;
        LocalDateTime releasedDateTime = TicketTestFixtures.releaseDateTime;
        LocalDateTime deadLineDateTime = TicketTestFixtures.deadLineDateTime;
        TicketType ticketType = TicketTestFixtures.typeVIP;

        //when
        int ticketStock = 0;
        ticketInventory.addTickets(ticketType, ticketStock, ticketPrice, releasedDateTime,
            deadLineDateTime);

        //then
        assertThatThrownBy(
            () -> Event.createEvent(eventName, eventVenue, host, sponsor,
                ticketInventory,
                eventStartDateTime, eventEndDateTime, announcement))
            .isInstanceOf(EventCreateTicketNegativeException.class)
            .hasMessage(EventCreateTicketNegativeException.MESSAGE);
    }


    /*
    티켓 구매 관련 테스트
     */

    @Test
    @DisplayName("티켓의 상태가 판매 중일 때만 티켓을 구매할 수 있다.")
    void canBuyTicketWhenTicketStatusForSale() {
    }

    @Test
    @DisplayName("티켓은 이벤트 시작 30일 이전부터 10일전까지만 판매를 개시할 수 있다.")
    void createTicketOnlyEventStartBefore30DayBetween10Day() {

    }


    @Test
    @DisplayName("티켓은 하나의 이벤트에 대해 사용자당 최대 2매를 초과할 수 없다.")
    void failToBuyTicketMoreThan2PerEvent() throws Exception {

    }

    @Test
    @DisplayName("이미 판매된 티켓은 구매할 수 없다.")
    void failToBuyTicketStatusIsSold() {

    }

    @Test
    @DisplayName("판매 전인 티켓은 구매할 수 없다.")
    void failToBuyTicketStatusIsNotReleased() {

    }

}
