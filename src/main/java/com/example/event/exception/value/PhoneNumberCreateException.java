package com.example.event.exception.value;

import com.example.event.exception.BusinessException;
import com.example.event.exception.ErrorCode;

public class PhoneNumberCreateException extends BusinessException {
    public PhoneNumberCreateException(){
        super(ErrorCode.PHONENUMBER_FORMAT_ERROR);
    }
}
