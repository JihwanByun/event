package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class EventCreateTicketNegativeException extends BusinessException {

    public EventCreateTicketNegativeException(){
        super(ErrorCode.EVENT_TICKET_CREATE_ERROR);
    }
}
