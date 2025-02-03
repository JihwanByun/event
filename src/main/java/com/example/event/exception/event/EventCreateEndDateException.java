package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventCreateEndDateException extends BusinessException {

    private static final Logger log = LoggerFactory.getLogger(EventCreateEndDateException.class);

    public EventCreateEndDateException() {
        super(ErrorCode.EVENT_ENDDATE_CREATE_ERROR);
    }

    public static void logInvalidEndDate(LocalDateTime inputEndTime, LocalDateTime thresholdTime) {
        log.warn("이벤트 종료 일정 생성 오류 발생 입력 시각: {}, 임계 시각 : {}", inputEndTime, thresholdTime);
    }
}
