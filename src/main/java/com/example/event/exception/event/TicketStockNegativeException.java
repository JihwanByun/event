package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class TicketStockNegativeException extends BusinessException {

    public static final String MESSAGE = ErrorCode.TICKET_STOCK_ERROR.getMessage();

    public TicketStockNegativeException() {
        super(ErrorCode.TICKET_STOCK_ERROR);
    }
}
