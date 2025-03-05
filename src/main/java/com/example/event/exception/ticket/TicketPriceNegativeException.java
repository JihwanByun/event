package com.example.event.exception.ticket;

import com.example.event.exception.BusinessException;

public class TicketPriceNegativeException extends BusinessException {

    private static final String MESSAGE_FORMAT = "티켓 판매 가격 설정 오류: %s는 0원보다 높아야합니다.";

    public TicketPriceNegativeException(int price) {
        super(String.format(MESSAGE_FORMAT, price));
    }

    public static String createMessage(int price) {
        return String.format(MESSAGE_FORMAT, price);
    }
}
