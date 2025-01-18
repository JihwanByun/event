package com.example.event.domain;

import com.example.event.domain.value.HostManager;

public final class Host {

    private final String hostName;
    private final HostManager hostManager;

    public Host(String hostName, HostManager hostManager) {
        this.hostName = hostName;
        this.hostManager = hostManager;
    }
}
