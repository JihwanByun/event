package com.example.event;

import com.example.event.domain.value.HostName;
import com.example.event.domain.value.ManagerName;
import com.example.event.domain.value.PhoneNumber;
import com.example.event.domain.value.Host;
import com.example.event.domain.value.HostManager;
import com.example.event.domain.value.Email;

public class HostTestFixtures {

    public static Host createHost() {

        Email email = new Email("host@mail.com");
        PhoneNumber phoneNumber = new PhoneNumber("01012345678");

        HostManager hostManager = new HostManager(new ManagerName("김담당자"), email, phoneNumber);
        return new Host(new HostName("문화체육관광부"), hostManager);
    }
}
