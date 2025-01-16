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

	/*
		관리자 기능
	 */
	@Test
	@DisplayName("관리자는 이벤트 생성, 수정, 삭제가 가능합니다.")
	void createEvent(){

	}
	@Test
	@DisplayName("관리자는 좌석 및 티켓 수를 정할 수 있습니다.")
	void decideTotalTicket(){
		
	}


}
