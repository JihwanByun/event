package com.example.event.domain;

import com.example.event.domain.value.Email;
import com.example.event.domain.value.PhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ValueObjectTest {

    @Test
    @DisplayName("이메일 주소가 같으면 같은 객체로 판단한다.")
    void emailValidateSuccessfully() {

        //given
        String Email = "abc@abc.com";

        //when
        Email email1 = new Email(Email);
        Email email2 = new Email(Email);

        //then
        assertThat(email1).isEqualTo(email2);
    }

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
