package com.example.event.domain.value;


import com.example.event.exception.ticket.TicketTypeNotFoundException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TicketType {

    VIP("VIP"),
    R("R석"),
    S("S석");

    private final String value;

    public static TicketType from(String value) {
        return Arrays.stream(TicketType.values())
                .filter(type -> type.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new TicketTypeNotFoundException(value));
    }

}
