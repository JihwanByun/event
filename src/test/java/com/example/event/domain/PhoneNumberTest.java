package com.example.event.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.event.domain.value.PhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

    @Test
    @DisplayName("핸드폰 번호가 같으면 같은 객체로 판단한다.")
    void phoneNumberValidateSuccessfully() {

        //given
        String phoneNumber = "01012345678";

        //when
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneNumber);
        PhoneNumber phoneNumber2 = new PhoneNumber(phoneNumber);

        //then
        assertThat(phoneNumber1).isEqualTo(phoneNumber2);
    }
}
