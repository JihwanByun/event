package com.example.event.exception.ticket;

import com.example.event.domain.value.TicketType;
import com.example.event.exception.BusinessException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TicketTypeNotFoundException extends BusinessException {
    private static final String MESSAGE_FORMAT = "티켓 타입은 %s은 존재하지 않습니다. 사용 가능한 티켓 타입은 %S 입니다.";

    public TicketTypeNotFoundException(String invalidType) {
        super(String.format(MESSAGE_FORMAT, invalidType, getAvailableTicketTypes()));
    }

    private static String getAvailableTicketTypes() {
        return Arrays.stream(TicketType.values())
                .map(TicketType::getValue)
                .collect(Collectors.joining(", "));
    }
}
