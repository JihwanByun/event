package com.example.event.domain.value;

import lombok.Getter;

public final class HostManager {

    @Getter
    private final ManagerName name;

    @Getter
    private final Email email;

    @Getter
    private final PhoneNumber phoneNumber;

    public HostManager(ManagerName name, Email email, PhoneNumber phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
