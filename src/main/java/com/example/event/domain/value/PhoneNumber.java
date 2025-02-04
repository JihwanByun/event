package com.example.event.domain.value;

import com.example.event.exception.value.PhoneNumberCreateException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class PhoneNumber {

    private static final String PHONE_REGEX = "^\\d{11}$";

    @Getter
    private final String value;

    public PhoneNumber(String value) {
        if (!isValid(value)) {
            throw new PhoneNumberCreateException();
        }
        this.value = value;
    }

    private boolean isValid(String phoneNumber) {
        return phoneNumber.matches(PHONE_REGEX);
    }
}
