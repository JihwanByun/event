package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class EventCreateStartDateException extends BusinessException {

    public EventCreateStartDateException() {
        super(ErrorCode.EVENT_STARTDATE_CREATE_ERROR);
    }
}
