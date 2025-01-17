package com.example.event.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

    //Event
    EVENT_TICKET_CREATE_ERROR(400, "Number of Ticket can't zero or negative"),
    EVENT_OPENDATE_CREATE_ERROR(400, "Event can be hold more in 3 Days"),
    EVENT_ENDDATE_CREATE_ERROR(400, "Event can be hold after open time" );


    @Getter
    private final String message;
    @Getter
    private int status;

    ErrorCode(final int status, final String message){
        this.status = status;
        this.message = message;
    }

}
