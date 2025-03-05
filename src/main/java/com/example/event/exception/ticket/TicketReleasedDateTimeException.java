package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;
import java.time.LocalDateTime;

public class TicketReleasedDateTimeException extends BusinessException {

    private static final String MESSAGE_FORMAT = "티켓 판매 시각 설정 오류: %s는 이벤트 시작 30일전부터 10일전까지만 가능합니다.";

    public TicketReleasedDateTimeException(LocalDateTime releaseDateTime) {
        super(String.format(MESSAGE_FORMAT, releaseDateTime));
    }

    public static String createMessage(LocalDateTime releaseDateTime) {
        return String.format(MESSAGE_FORMAT, releaseDateTime);
    }
}
