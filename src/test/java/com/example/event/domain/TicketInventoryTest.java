package com.example.event.domain;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.event.EventTestFixtures;
import java.time.LocalDateTime;
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
            LocalDateTime deadLineDateTime = event.getEndDateTime().minusDays(10);

            //when
            TicketInventory ticketInventory = TicketInventory.createTicketInventoryOfEventWithSalesDuration(event,
                    releasedDateTime, deadLineDateTime);

            //then
            assertThat(ticketInventory.getTicketReleaseDateTime()).isEqualTo(releasedDateTime);
            assertThat(ticketInventory.getTicketSaleDeadLineDateTime()).isEqualTo(deadLineDateTime);
        }
    }
}
