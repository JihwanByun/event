package com.example.event.domain.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ManagerName {

    private final String name;

    public ManagerName(String name) {
        this.name = name;
    }
}
