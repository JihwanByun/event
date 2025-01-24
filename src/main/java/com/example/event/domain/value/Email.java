package com.example.event.domain.value;

import com.example.event.exception.value.EmailCreateException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String address;

    public Email(String address) {
        if (!isValid(address)) {
            throw new EmailCreateException();
        }
        this.address = address;
    }

    private boolean isValid(String email) {
        return PATTERN.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return Objects.equals(email.address, address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }
}
