package com.example.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventApplicationTests {

	@Test
	@DisplayName("사용자는 조건(이름,날짜,장소,가격,좌석 종류)에 따라 이벤트 목록을 검색할 수 있습니다.")
	void searchEventByCondition() {
	}

	@Test
	@DisplayName("사용자는 티켓 예약 및 결제를 진행할 수 있습니다.")
	void reserveTicket(){
	}

	@Test
	@DisplayName("사용자는 예약 확정된 티켓에 대해 이벤트 시작 24시간 전까지 취소가 가능합니다.")
	void cancelTicket(){
	}

	@Test
	@DisplayName("환불 규정에 따라 일주일 전 취소한 티켓의 경우 100%, 3일전의 경우 50% 환불이 가능하며, 당일 환불은 불가합니다. ")
	void refundTicketPrice(){
	}

	/*
		관리자 기능
	 */
	@Test
	@DisplayName("관리자는 현재보다 3일 이상 남은 이벤트에 대해서만 새로운 이벤트를 생성할 수 있습니다.")
	void createEvent(){
	}

	@Test
	@DisplayName("관리자는 좌석 및 티켓 수를 정할 수 있습니다.")
	void decideTotalTicket(){

	}


}
