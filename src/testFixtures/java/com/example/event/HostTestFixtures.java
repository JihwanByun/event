package com.example.event;

import com.example.event.domain.Host;

public class HostTestFixtures {

    public static Host createHost() {
        return new Host("떡잎호스트", "김담당자", "host@mail.com", "010-0000-0000");
    }
}
