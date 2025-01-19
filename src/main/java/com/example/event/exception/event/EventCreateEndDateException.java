package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class EventCreateEndDateException extends BusinessException {

    public static final String MESSAGE = ErrorCode.EVENT_ENDDATE_CREATE_ERROR.getMessage();

    public EventCreateEndDateException() {
        super(ErrorCode.EVENT_ENDDATE_CREATE_ERROR);
    }
}
