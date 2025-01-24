package com.example.event.domain;

import com.example.event.TicketTestFixtures;
import com.example.event.domain.value.TicketType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TicketTest {


    Ticket ticket = TicketTestFixtures.createTicket();

    /*
    티켓 구매 관련 테스트
     */

    @Test
    @DisplayName("티켓의 상태가 판매 중일 때만 티켓을 구매할 수 있다.")
    void canBuyTicketWhenTicketStatusForSale() {
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

    @Test
    @DisplayName("티켓은 이벤트 시작 30일 이전부터 10일전까지만 판매를 개시할 수 있다.")
    void createTicketOnlyEventStartBefore30DayBetween10Day(){

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
