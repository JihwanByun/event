package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventCreateStartDateException extends BusinessException {

    private static final Logger log = LoggerFactory.getLogger(EventCreateStartDateException.class);

    public EventCreateStartDateException() {
        super(ErrorCode.EVENT_STARTDATE_CREATE_ERROR);
    }

    public static void logInvalidStartDate(LocalDateTime inputEventDateTime) {
        log.warn("이벤트 시작 일자 생성 오류 발생: 입력 시각: {}, 임계 시각: {}", inputEventDateTime,
            LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(3));
    }
}
