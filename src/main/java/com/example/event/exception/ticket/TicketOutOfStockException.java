package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class TicketOutOfStockException extends BusinessException {
    public static String MESSAGE = ErrorCode.TICKET_OUT_OF_STOCK_ERROR.getMessage();

    public TicketOutOfStockException(){
        super(ErrorCode.TICKET_OUT_OF_STOCK_ERROR);
    }
}
