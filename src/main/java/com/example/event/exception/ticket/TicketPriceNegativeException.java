package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class TicketPriceNegativeException extends BusinessException {

    public static final String MESSAGE = ErrorCode.TICKET_PRICE_ERROR.getMessage();

    public TicketPriceNegativeException() {
        super(ErrorCode.TICKET_PRICE_ERROR);
    }
}
