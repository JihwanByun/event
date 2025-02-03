package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventCreateTicketNegativeException extends BusinessException {

    private static final Logger log = LoggerFactory.getLogger(EventCreateTicketNegativeException.class);

    public EventCreateTicketNegativeException() {
        super(ErrorCode.EVENT_TICKET_CREATE_ERROR);
    }

    public static void invalidTicketStock(int inputStock) {
        log.warn("티켓 개수 생성 오류 발생: 입력 판매 티켓 수: {}, 최소 판매 가능 티켓 수: 1", inputStock);
    }
}
