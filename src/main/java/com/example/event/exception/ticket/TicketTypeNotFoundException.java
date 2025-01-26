package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class TicketTypeNotFoundException extends BusinessException {
    public static String MESSAGE = ErrorCode.TICKETTYPE_NOT_FOUND_ERROR.getMessage();

    public TicketTypeNotFoundException(){
        super(ErrorCode.TICKETTYPE_NOT_FOUND_ERROR);
    }
}
