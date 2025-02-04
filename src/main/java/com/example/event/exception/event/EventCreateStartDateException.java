package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import java.time.LocalDateTime;

public class EventCreateStartDateException extends BusinessException {

    private static final String MESSAGE_FORMAT = "이벤트 시작일 생성 오류 발생: 입력값 %s, 임계값 %s";

    public EventCreateStartDateException(LocalDateTime startDate) {
        super(String.format(MESSAGE_FORMAT, startDate, startDate.plusDays(3)));
    }

    public static String createMessage(LocalDateTime startDate) {
        return String.format(MESSAGE_FORMAT, startDate, startDate.plusDays(3));
    }
}
