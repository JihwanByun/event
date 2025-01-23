package com.example.event.exception.value;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class EmailCreateException extends BusinessException {
    public static final String MESSAGE = ErrorCode.EMAIL_FORMAT_ERROR.getMessage();

    public EmailCreateException(){
        super(ErrorCode.EMAIL_FORMAT_ERROR);
    }
}
