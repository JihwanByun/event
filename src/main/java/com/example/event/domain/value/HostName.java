package com.example.event.domain.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class HostName {

    private final String value;

    public HostName(String value) {
        this.value = value;
    }
}
