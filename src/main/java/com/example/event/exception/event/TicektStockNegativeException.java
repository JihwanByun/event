package com.example.event.exception.event;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class TicektStockNegativeException extends BusinessException {

    public static final String MESSAGE = ErrorCode.TICKET_STOCK_ERROR.getMessage();

    public TicektStockNegativeException() {
        super(ErrorCode.TICKET_STOCK_ERROR);
    }
}
