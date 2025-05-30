package com.example.event.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.event.AnnouncementTestFixtures;
import com.example.event.HostTestFixtures;
import com.example.event.SponsorTestFixtures;
import com.example.event.VenueTestFixtures;
import com.example.event.domain.value.Host;
import com.example.event.domain.value.Sponsor;
import com.example.event.domain.value.Venue;
import com.example.event.exception.event.EventCreateEndDateException;
import com.example.event.exception.event.EventCreateStartDateException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class EventTest {


    @Nested
    @DisplayName("이벤트 생성 실패 케이스")
    class EventCreationFailureTests {

        @Test
        @DisplayName("이벤트는 종료 시간이 시작 시간보다 빠르면 예외가 발생한다.")
        public void shouldThrowExceptionWhenEndDateIsBeforeStartDate() {

            //given
            String eventName = "OO 이벤트";
            Venue eventVenue = new Venue("서울 올림픽 공원", 100);
            Host host = HostTestFixtures.createHost();
            Sponsor sponsor = SponsorTestFixtures.createSponsor();
            LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(4);
            Announcement announcement = AnnouncementTestFixtures.createAnnouncement();

            //when
            LocalDateTime eventEndDateTime = eventStartDateTime.minusDays(1);

            //then
            assertThatThrownBy(
                    () -> Event.createEvent(eventName, eventVenue, host, sponsor,
                            eventStartDateTime,
                            eventEndDateTime, announcement))
                    .isInstanceOf(EventCreateEndDateException.class)
                    .hasMessage(EventCreateEndDateException.createMessage(eventEndDateTime,
                            eventStartDateTime));
        }

        @Test
        @DisplayName("이벤트 시작 시간이 현재 시간 기준 3일 미만일 경우 예외가 발생한다.")
        public void addEventStartDateTime() {

            //given
            String eventName = "OO 이벤트";
            Venue eventVenue = VenueTestFixtures.createVenue();
            Host host = HostTestFixtures.createHost();
            Sponsor sponsor = SponsorTestFixtures.createSponsor();
            Announcement announcement = AnnouncementTestFixtures.createAnnouncement();

            //when
            LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(2);
            LocalDateTime eventEndDateTime = eventStartDateTime.plusDays(2);

            //then
            assertThatThrownBy(
                    () -> Event.createEvent(eventName, eventVenue, host, sponsor,
                            eventStartDateTime, eventEndDateTime, announcement))
                    .isInstanceOf(EventCreateStartDateException.class)
                    .hasMessage(EventCreateStartDateException.createMessage(eventStartDateTime));
        }
    }


    @Nested
    @DisplayName("이벤트 생성 성공 케이스")
    class EventCreationSuccessTests {

        @Test
        @DisplayName("이벤트 시작 시간이 현재 시간 기준 3일 이후이고, 종료 시간은 시작 시간보다 이후이며,"
                + " 판매 가능한 티켓 수는 1장 이상일 때, 이벤트를 생성할 수 있다.")
        public void createEventSuccessfully() {

            //given
            String eventName = "OO 이벤트";
            Venue eventVenue = new Venue("서울 올림픽 공원", 100);
            Host host = HostTestFixtures.createHost();
            Sponsor sponsor = SponsorTestFixtures.createSponsor();
            Announcement announcement = AnnouncementTestFixtures.createAnnouncement();

            //when
            LocalDateTime eventStartDateTime = LocalDateTime.now().plusDays(4);
            LocalDateTime eventEndDateTime = eventStartDateTime.plusDays(1);
            Event event = Event.createEvent(eventName, eventVenue, host, sponsor,
                    eventStartDateTime,
                    eventEndDateTime,
                    announcement);

            //then
            assertThat(event).isNotNull();
            assertThat(event.getEventName()).isEqualTo(eventName);
            assertThat(event.getVenue()).isEqualTo(eventVenue);
            assertThat(event.getHost()).isEqualTo(host);
            assertThat(event.getSponsor()).isEqualTo(sponsor);
            assertThat(event.getStartDateTime()).isEqualTo(eventStartDateTime);
            assertThat(event.getEndDateTime()).isEqualTo(eventEndDateTime);
        }
    }
}
