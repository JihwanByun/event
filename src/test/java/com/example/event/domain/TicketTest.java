package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TicketTest {

    @Test
    @DisplayName("판매할 티켓 수가 0장 이하인 경우 예외가 발생한다.")
    void shouldThrowExceptionWhenTotalTicketNumberIsZeroOrNegative() {

        //given
        TicketInventory ticketInventory = EventTestFixtures.tickets;
        int ticketPrice = TicketTestFixtures.price;
        LocalDateTime releasedDateTime = TicketTestFixtures.releaseDateTime;
        LocalDateTime deadLineDateTime = TicketTestFixtures.deadLineDateTime;
        TicketType ticketType = TicketTestFixtures.typeVIP;

        //when
        int ticketStock = 0;

        //then
        assertThatThrownBy(
            () -> ticketInventory.addTickets(ticketType, ticketStock, ticketPrice, releasedDateTime,
                deadLineDateTime))
            .isInstanceOf(TicketStockNegativeException.class)
            .hasMessage(TicketStockNegativeException.MESSAGE);
    }

    @Test
    @DisplayName("티켓은 이벤트 시작 30일 이전부터 10일전까지만 판매할 수 있다.")
    void createTicketOnlyEventStartBefore30DayBetween10Day() {

        //given
        Event event = EventTestFixtures.createEvent();
        TicketType ticketType = TicketTestFixtures.typeVIP;
        int stock = TicketTestFixtures.stock;
        int price = TicketTestFixtures.price;

        //when
        TicketInventory ticketInventory = TicketInventory.createTicketInventoryOfEvent(event);
        LocalDateTime eventStartDateTime = event.getStartDateTime();
        LocalDateTime ticketReleaseDateTime = event.getStartDateTime().minusDays(30);
        LocalDateTime ticketDeadLineDateTime = event.getStartDateTime().minusDays(10);
        ticketInventory.addTickets(ticketType, stock, price, ticketReleaseDateTime,
            ticketDeadLineDateTime);

        //then
        assertThat(
            ticketInventory.getTickets().get(ticketType).get(0).getReleaseDateTime()).isEqualTo(
            ticketReleaseDateTime);
        assertThat(
            ticketInventory.getTickets().get(ticketType).get(0).getDeadLineDateTime()).isEqualTo(
            ticketDeadLineDateTime);
    }


    /*
    티켓 구매 관련 테스트
     */

    @Test
    @DisplayName("티켓의 상태가 판매 중일 때만 티켓을 구매할 수 있다.")
    void canBuyTicketWhenTicketStatusForSale() {
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
