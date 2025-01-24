package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class TicketReleasedDateTimeException extends BusinessException {
    public static final String MESSAGE = ErrorCode.TICKET_RELEASED_DATETIME_ERROR.getMessage();

    public TicketReleasedDateTimeException (){
        super(ErrorCode.TICKET_RELEASED_DATETIME_ERROR);
    }
}
