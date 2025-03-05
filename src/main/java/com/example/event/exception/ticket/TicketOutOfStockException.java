package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;

public class TicketOutOfStockException extends BusinessException {
    public static String MESSAGE_FORMANT = "티켓 등록 오류: %s는 1개 이상이어야 합니다.";

    public TicketOutOfStockException(int stock) {
        super(String.format(MESSAGE_FORMANT, stock));
    }

    public static String createMessage(int stock) {
        return String.format(MESSAGE_FORMANT, stock);
    }

}
