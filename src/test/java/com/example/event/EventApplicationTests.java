package com.example.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventApplicationTests {


    @Test
    @DisplayName("사용자는 조건(이름,날짜,장소,가격,좌석 종류)에 따라 이벤트 목록을 검색할 수 있습니다.")
    void searchEventByCondition() {

    }

    @Test
    @DisplayName("사용자는 3분 이내에 판매 중인 티켓에 대해 예매할 수 있으며, 이벤트당 최대 구매 가능 개수는 2개입니다.")
    void buyTicket() {
    }

    @Test
    @DisplayName("사용자는 공연 시작 1시간 전까지 예약 확정된 티켓에 대해 취소가 가능합니다.")
    void cancelTicket() {
    }

	/*
		관리자 기능
	 */




    @Test
    @DisplayName("관리자는 좌석 및 티켓의 종류와 종류별 판매량을 정할 수 있습니다.")
    void decideTotalTicket() {

    }


}
