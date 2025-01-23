package com.example.event.domain;

import com.example.event.domain.value.Email;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValueObjectTest {

    @Test
    @DisplayName("이메일 주소가 같으면 같은 값으로 판단한다.")
    void valueObjectAssertion(){

        //given
        Email email1 = new Email("abc@abc.com");
        Email email2 = new Email("abc@abc.com");

        //then
        Assertions.assertThat(email1).isEqualTo(email2);
    }
}
