package com.example.event.domain;

import com.example.event.EventTestFixtures;
import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TicketTest {

    @Nested
    @DisplayName("티켓 판매 등록 실패 케이스")
    class TicketCreationFailureTests {

        @Test
        @DisplayName("판매할 티켓 수가 0장 이하인 경우 예외가 발생한다.")
        void shouldThrowExceptionWhenTotalTicketNumberIsZeroOrNegative() {

            //given
            Event event = EventTestFixtures.createEvent();
            TicketInventory ticketInventory = TicketTestFixtures.createTicketInventoryOfEvent(
                event);
            int ticketPrice = 1000;
            LocalDateTime releasedDateTime = LocalDateTime.now().plusDays(5);
            LocalDateTime deadLineDateTime = LocalDateTime.now().plusDays(6);
            TicketType ticketType = new TicketType("VIP");

            //when
            int ticketStock = 0;

            //then
            assertThatThrownBy(
                () -> ticketInventory.addTickets(ticketType, ticketStock, ticketPrice,
                    releasedDateTime,
                    deadLineDateTime))
                .isInstanceOf(TicketStockNegativeException.class)
                .hasMessage(TicketStockNegativeException.createMessage(ticketStock));
        }
    }

    @Nested
    @DisplayName("티켓 판매 등록 성공 케이스")
    class TicketCreationSuccessTests {

        @Test
        @DisplayName("티켓은 이벤트 시작 30일 이전부터 10일전까지만 판매할 수 있다.")
        void createTicketOnlyEventStartBefore30DayBetween10Day() {

            //given
            Event event = EventTestFixtures.createEvent();
            TicketType ticketType = new TicketType("VIP");
            int stock = 10;
            int price = 1000;
            TicketInventory ticketInventory = TicketInventory.createTicketInventoryOfEvent(event);

            //when
            LocalDateTime eventStartDateTime = event.getStartDateTime();
            LocalDateTime ticketReleaseDateTime = event.getStartDateTime().minusDays(30);
            LocalDateTime ticketDeadLineDateTime = event.getStartDateTime().minusDays(10);
            ticketInventory.addTickets(ticketType, stock, price, ticketReleaseDateTime,
                ticketDeadLineDateTime);

            //then
            assertThat(
                ticketInventory.getAvailableTickets().get(ticketType).get(0)
                    .getReleaseDateTime()).isEqualTo(
                ticketReleaseDateTime);
            assertThat(
                ticketInventory.getAvailableTickets().get(ticketType).get(0)
                    .getDeadLineDateTime()).isEqualTo(
                ticketDeadLineDateTime);
        }
    }

    @Nested
    @DisplayName("티켓 구매 성공 케이스")
    class TicketPaymentSuccessTests {

        @Test
        @DisplayName("티켓의 재고가 구매 수량보다 많고, 존재하는 티켓 Type에 대해서만 구매할 수 있다.")
        void buyTicketWithStatusAndStockSuccessfully() {

            //given
            Event event = EventTestFixtures.createEvent();
            TicketInventory ticketInventory = TicketTestFixtures.createTicketInventoryOfEvent(
                event);
            int stock = 100;
            TicketType ticketType = new TicketType("VIP");

            //when
            int numberOfBuyingTicket = 2;
            ticketInventory.buyTicketWithType(numberOfBuyingTicket, ticketType);

            //then
            assertThat(ticketInventory.getAvailableTickets().get(ticketType).size()).isEqualTo(
                stock - numberOfBuyingTicket);
            assertThat(ticketInventory.getSoldTickets().get(ticketType).size()).isEqualTo(
                numberOfBuyingTicket);
            assertThat(ticketInventory.getSoldTickets().get(ticketType).stream()
                .allMatch(ticket -> ticket.getStatus() == TicketStatus.SOLD)).isTrue();

        }
    }
}
