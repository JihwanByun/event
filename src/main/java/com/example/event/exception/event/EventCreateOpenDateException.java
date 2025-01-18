package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class EventCreateOpenDateException extends BusinessException {

    public EventCreateOpenDateException() {
        super(ErrorCode.EVENT_OPENDATE_CREATE_ERROR);
    }
}
