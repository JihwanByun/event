package com.example.event.exception.event;

import com.example.event.exception.BusinessException;

public class EventCreateTicketNegativeException extends BusinessException {

    private static final String MESSAGE_FORMAT = "이벤트 생성 티켓 판매 재고 오류 발생 %d, 티켓은 하나 이상 판매해야합니다.";

    public EventCreateTicketNegativeException(int totalTicketStock) {
        super(String.format(MESSAGE_FORMAT, totalTicketStock));
    }

    public static String createMessage(int totalTicketStock) {
        return String.format(MESSAGE_FORMAT, totalTicketStock);
    }
}
