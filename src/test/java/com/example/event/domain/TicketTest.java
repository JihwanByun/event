package com.example.event.domain;

import com.example.event.TicketTestFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
    TODO
 */
public class TicketTest {


    Ticket ticket = TicketTestFixtures.createTicket();

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

}
