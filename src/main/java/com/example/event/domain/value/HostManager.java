package com.example.event.domain.value;

import lombok.Getter;

public final class HostManager {

    @Getter
    private final String managerName;

    @Getter
    private final Email email;

    @Getter
    private final PhoneNumber phoneNumber;

    public HostManager(String managerName, Email email, PhoneNumber phoneNumber) {
        this.managerName = managerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
