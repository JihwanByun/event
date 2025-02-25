package com.example.event.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.event.EventTestFixtures;
import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import com.example.event.exception.ticket.TicketOutOfStockException;
import com.example.event.exception.ticket.TicketReleasedDateTimeException;
import com.example.event.exception.ticket.TicketTypeNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TicketInventoryTest {

    @Nested
    @DisplayName("티켓 인벤토리 생성 성공")
    class createTicketInventorySuccessful {

        @Test
        @DisplayName("티켓은 이벤트 시작 30일 이전부터 10일전까지만 판매할 수 있다.")
        void createTicketInventoryOnlyEventStartBefore30DayBetween10Day() {
            //given
            Event event = EventTestFixtures.createEvent();
            LocalDateTime releasedDateTime = event.getStartDateTime().minusDays(30);
            LocalDateTime deadLineDateTime = event.getStartDateTime().minusDays(10);

            //when
            TicketInventory ticketInventory = TicketInventory.createTicketInventoryOfEventWithSalesDuration(
                event,
                releasedDateTime, deadLineDateTime);

            //then
            assertThat(ticketInventory.getTicketReleaseDateTime()).isEqualTo(releasedDateTime);
            assertThat(ticketInventory.getTicketSaleDeadLineDateTime()).isEqualTo(deadLineDateTime);
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
            TicketType ticketType = TicketType.VIP;
            List<Ticket> tickets = TicketTestFixtures.addTicketsWithTypeAndQuantity(ticketType,
                stock);
            ticketInventory.addTickets(tickets);

            //when
            int numberOfBuyingTicket = 2;
            ticketInventory.buyTicketWithTypeAndQuantity(ticketType, numberOfBuyingTicket);

            //then
            List<Ticket> availableTickets = ticketInventory.findAvailableTicketsByType(ticketType);
            List<Ticket> soldTickets = ticketInventory.findSoldTicketsByType(ticketType);

            assertThat(availableTickets.size()).isEqualTo(stock - numberOfBuyingTicket);
            assertThat(soldTickets.size()).isEqualTo(numberOfBuyingTicket);
            assertThat(soldTickets.stream()
                .allMatch(ticket -> ticket.getStatus() == TicketStatus.SOLD))
                .isTrue();
        }
    }

    @Nested
    @DisplayName("티켓 구매 실패 케이스")
    class TicketPaymentFailureTests {

        @Test
        @DisplayName("등록된 티켓의 타입이 아닌 경우 티켓 구매에 실패한다.")
        void shouldThrowExceptionWhenTicketTypeIsNotEnrolled() {
            //given
            Event event = EventTestFixtures.createEvent();
            TicketInventory ticketInventory = TicketTestFixtures.createTicketInventoryOfEvent(
                event);
            TicketType typeVIP = TicketType.VIP;

            //when
            List<Ticket> tickets = TicketTestFixtures.addTicketsWithTypeAndQuantity(typeVIP, 100);
            ticketInventory.addTickets(tickets);

            //then
            assertThatThrownBy(
                () -> ticketInventory.buyTicketWithTypeAndQuantity(TicketType.R, 10))
                .isInstanceOf(TicketTypeNotFoundException.class)
                .hasMessage(TicketTypeNotFoundException.createMessage(TicketType.R.getValue()));
        }

        @Test
        @DisplayName("티켓 구매 수량이 재고 수량보다 많으면 구매에 실패한다.")
        void shouldThrowExceptionWhenBuyingTicketIsMoreThanStock() {
            //given
            Event event = EventTestFixtures.createEvent();
            TicketInventory ticketInventory = TicketTestFixtures.createTicketInventoryOfEvent(
                event);
            TicketType typeVIP = TicketType.VIP;

            //when
            List<Ticket> tickets = TicketTestFixtures.addTicketsWithTypeAndQuantity(typeVIP, 100);
            ticketInventory.addTickets(tickets);

            //then
            assertThatThrownBy(
                () -> ticketInventory.buyTicketWithTypeAndQuantity(typeVIP, tickets.size() + 1))
                .isInstanceOf(TicketOutOfStockException.class)
                .hasMessage(TicketOutOfStockException.createMessage(tickets.size()));
        }
    }

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

            //when
            List<Ticket> tickets = new ArrayList<>();

            //then
            assertThatThrownBy(
                () -> ticketInventory.addTickets(tickets))
                .isInstanceOf(TicketStockNegativeException.class)
                .hasMessage(TicketStockNegativeException.createMessage(tickets.size()));
        }

        @Test
        @DisplayName("티켓 판매 시작 날짜가 이벤트 시작 날짜보다 30일 초과로 빠르면 예외가 발생한다.")
        void shouldThrowExceptionWhenReleasedTimeIsEarlierThanEventStartDateMore30Days() {

            //given
            Event event = EventTestFixtures.createEvent();

            //when
            LocalDateTime eventStartDateTime = event.getStartDateTime();
            LocalDateTime releasedDate = eventStartDateTime.minusDays(31);
            LocalDateTime deadlineDate = eventStartDateTime.plusDays(2);

            //then
            assertThatThrownBy(
                () -> TicketInventory.createTicketInventoryOfEventWithSalesDuration(
                    event, releasedDate, deadlineDate))
                .isInstanceOf(TicketReleasedDateTimeException.class)
                .hasMessage(TicketReleasedDateTimeException.createMessage(releasedDate));
        }

        @Test
        @DisplayName("티켓 판매 마감 날짜가 이벤트 시작 날짜보다 10일 이전이면 예외가 발생한다.")
        void shouldThrowExceptionWhenDeadLineTimeIsEarlierThanEventStartDateLess10Days() {

            //given
            Event event = EventTestFixtures.createEvent();

            //when
            LocalDateTime eventStartDateTime = event.getStartDateTime();
            LocalDateTime releasedDate = eventStartDateTime.minusDays(12);
            LocalDateTime deadlineDate = eventStartDateTime.minusDays(9);

            //then
            assertThatThrownBy(
                () -> TicketInventory.createTicketInventoryOfEventWithSalesDuration(
                    event, releasedDate, deadlineDate))
                .isInstanceOf(TicketReleasedDateTimeException.class)
                .hasMessage(TicketReleasedDateTimeException.createMessage(releasedDate));
        }

        @Test
        @DisplayName("티켓 판매 마감 날짜가 판매 시작 날짜보다 빠르면 예외가 발생한다.")
        void shouldThrowExceptionWhenDeadLineTimeIsEarlierThanReleasedTime() {

            //given
            Event event = EventTestFixtures.createEvent();

            //when
            LocalDateTime eventStartDateTime = event.getStartDateTime();
            LocalDateTime releasedDate = eventStartDateTime.minusDays(12);
            LocalDateTime deadlineDate = releasedDate.minusDays(1);

            //then
            assertThatThrownBy(
                () -> TicketInventory.createTicketInventoryOfEventWithSalesDuration(
                    event, releasedDate, deadlineDate))
                .isInstanceOf(TicketReleasedDateTimeException.class)
                .hasMessage(TicketReleasedDateTimeException.createMessage(releasedDate));
        }
    }

    @Test
    @DisplayName("판매된 티켓과 판매 중인 티켓 전체 티켓을 가져올 수 있다.")
    void getTotalTicketsEnrolled() {

        //given
        Event event = EventTestFixtures.createEvent();
        TicketInventory ticketInventory = TicketTestFixtures.createTicketInventoryOfEvent(event);
        TicketType ticketType = TicketType.VIP;
        ticketInventory.addTickets(
            TicketTestFixtures.addTicketsWithTypeAndQuantity(ticketType, 100));
        ticketInventory.buyTicketWithTypeAndQuantity(ticketType, 30);

        //when
        int totalTickets = ticketInventory.getTotalTicketQuantity();

        //then
        assertThat(totalTickets).isEqualTo(
            ticketInventory.findAvailableTicketsByType(ticketType).size() +
                ticketInventory.findSoldTicketsByType(ticketType).size());
    }
}
