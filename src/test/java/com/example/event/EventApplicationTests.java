package com.example.event;

import com.example.event.domain.Event;
import com.example.event.domain.Host;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

class EventApplicationTests {


	@Test
	@DisplayName("사용자는 조건(이름,날짜,장소,가격,좌석 종류)에 따라 이벤트 목록을 검색할 수 있습니다.")
	void searchEventByCondition() {

	}

	@Test
	@DisplayName("사용자는 3분 이내에 판매 중인 티켓에 대해 예매할 수 있으며, 이벤트당 최대 구매 가능 개수는 2개입니다.")
	void buyTicket(){
	}

	@Test
	@DisplayName("사용자는 공연 시작 1시간 전까지 예약 확정된 티켓에 대해 취소가 가능합니다.")
	void cancelTicket(){
	}

	/*
		관리자 기능
	 */


	@Test
	@DisplayName("관리자는 현재 시간보다 3일 이상 남은 이벤트에 대해서만 새로운 이벤트를 생성할 수 있으며, 종료 시각이 현재 시각보다 빠를 수 없습니다." +
			"판매 가능한 티켓은 1장 이상입니다.")
	void createEvent(){
		//given
		int totalTicket = 40;
		LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(4);
		LocalDateTime eventEndDateTime = LocalDateTime.of(2025,1,28,4,30);

		//when
		Event event = testFixture.createEventWithTotalTicketAndStartTimeAndEndTime(totalTicket, eventStartDateTime, eventEndDateTime);

		//then
		Assertions.assertNotNull(event);
		Assertions.assertEquals(totalTicket, event.getTotalTicketCnt());
		Assertions.assertEquals(eventStartDateTime, event.getStartDateTime());
		Assertions.assertEquals(eventEndDateTime, event.getEndDateTime());
	}

	@Test
	@DisplayName("관리자는 좌석 및 티켓의 종류와 종류별 판매량을 정할 수 있습니다.")
	void decideTotalTicket(){

	}


}
