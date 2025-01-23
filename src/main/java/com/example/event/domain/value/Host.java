package com.example.event.domain.value;

public final class Host {

    private final HostName name;

    private final HostManager hostManager;

    public Host(HostName name, HostManager hostManager) {
        this.name = name;
        this.hostManager = hostManager;
    }
}
