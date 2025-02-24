package com.example.event.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.event.EventTestFixtures;
import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.TicketType;
import com.example.event.exception.event.TicketStockNegativeException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @Nested
    @DisplayName("티켓 판매 등록 실패 케이스")
    class TicketCreationFailureTests {

        @Test
        @DisplayName("판매할 티켓 수가 0장 이하인 경우 예외가 발생한다.")
        void shouldThrowExceptionWhenTotalTicketNumberIsZeroOrNegative() {

            //given
            Event event = EventTestFixtures.createEvent();
            TicketInventory ticketInventory = TicketTestFixtures.createTicketInventoryOfEvent(event);

            //when
            List<Ticket> tickets = new ArrayList<>();

            //then
            assertThatThrownBy(
                    () -> ticketInventory.addTickets(tickets))
                    .isInstanceOf(TicketStockNegativeException.class)
                    .hasMessage(TicketStockNegativeException.createMessage(tickets.size()));
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
            List<Ticket> tickets = TicketTestFixtures.addTicketsWithTypeAndQuantity(ticketType, stock);
            ticketInventory.addTickets(tickets);

            //when
            int numberOfBuyingTicket = 2;
            ticketInventory.buyTicketWithType(numberOfBuyingTicket, ticketType);

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
}
