package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import java.time.LocalDateTime;

public class EventCreateEndDateException extends BusinessException {

    private static final String MESSAGE_FORMAT = "이벤트 종료일 설정 오류: %s는 시작일 %s 이후여야 합니다.";

    public EventCreateEndDateException(LocalDateTime endDate, LocalDateTime startDate) {
        super(String.format(MESSAGE_FORMAT, endDate, startDate));
    }

    public static String createMessage(LocalDateTime endaDate, LocalDateTime startDate) {
        return String.format(MESSAGE_FORMAT, endaDate, startDate);
    }
}
