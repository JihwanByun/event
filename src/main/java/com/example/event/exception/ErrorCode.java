package com.example.event.exception;

import lombok.Getter;

public enum ErrorCode {

    //Event
    EVENT_TICKET_CREATE_ERROR(400, "Number of Ticket can't zero or negative"),
    EVENT_STARTDATE_CREATE_ERROR(400, "Event can be hold more in 3 Days"),
    EVENT_ENDDATE_CREATE_ERROR(400, "Event can be hold after open time"),

    //Email
    EMAIL_FORMAT_ERROR(400, "Follow the email format" ),

    //Phone
    PHONENUMBER_FORMAT_ERROR(400, "Follow the phone number format" );


    @Getter
    private final String message;
    @Getter
    private int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

}
