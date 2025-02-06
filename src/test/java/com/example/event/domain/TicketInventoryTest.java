package com.example.event.domain;


import com.example.event.EventTestFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TicketInventoryTest {

    @Test
    @DisplayName("티켓 인벤토리 생성 성공")
    public void createTicketInventorySuccessfully() {

        //given
        Event event = EventTestFixtures.createEvent();

        //when
        TicketInventory ticketInventory = TicketInventory.createTicketInventoryOfEvent(event);

        //then
        assertThat(ticketInventory).isNotNull();
        assertThat(ticketInventory.getEvent()).isEqualTo(event);
    }

}
