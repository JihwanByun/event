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
	@DisplayName("사용자는 3분 이내에 판매 중인 티켓에 대해 결제할 수 있습니다.")
	void buyTicket(){
	}

	@Test
	@DisplayName("사용자는 공연 시작 1시간 전 예약 확정된 티켓에 대해 취소가 가능합니다.")
	void cancelTicket(){
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
