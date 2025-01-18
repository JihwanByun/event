package com.example.event.domain.value;

public final class HostManager {

    private final String managerName;
    private final Email email;
    private final PhoneNumber phoneNumber;

    public HostManager(String managerName, Email email, PhoneNumber phoneNumber) {
        this.managerName = managerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
