package com.example.event.domain.value;

public final class Host {

    private final String name;
    private final HostManager hostManager;

    public Host(String name, HostManager hostManager) {
        this.name = name;
        this.hostManager = hostManager;
    }
}
