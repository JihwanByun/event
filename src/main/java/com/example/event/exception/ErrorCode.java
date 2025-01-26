package com.example.event.exception;

import lombok.Getter;

public enum ErrorCode {

    //Event
    EVENT_STARTDATE_CREATE_ERROR(400, "Event can be hold more in 3 Days"),
    EVENT_ENDDATE_CREATE_ERROR(400, "Event can be hold after open time"),

    //Email
    EMAIL_FORMAT_ERROR(400, "Follow the email format [@와 .을 포함한 이메일 형식을 지키세요]"),

    //Phone
    PHONENUMBER_FORMAT_ERROR(400, "Follow the phone number format [연속된 숫자 11자리 형식을 지키세요]"),

    //Ticket
    TICKET_STOCK_ERROR(400, "Number of Ticket can't zero or negative"),
    TICKET_PRICE_ERROR(400, "Ticket Price can't be negative"),
    TICKET_RELEASED_DATETIME_ERROR(400, "티켓 판매는 이벤트 시작 이전 30일 부터 10일전까지만 가능합니다."),
    TICKETTYPE_NOT_FOUND_ERROR(400, "해당 유형의 티켓을 찾을 수 없습니다. 티켓을 새로 등록하거나, 등록된 티켓을 입력하세요."),
    TICKET_OUT_OF_STOCK_ERROR(400, "구매 수량이 티켓의 재고 수량보다 많습니다.");


    @Getter
    private final String message;
    @Getter
    private int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

}
