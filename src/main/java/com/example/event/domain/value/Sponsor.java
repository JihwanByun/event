package com.example.event.domain.value;

import java.util.Objects;
import lombok.Getter;


public final class Sponsor {

    @Getter
    private final String sponsorInfo;

    public Sponsor(String sponsorInfo) {
        this.sponsorInfo = sponsorInfo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sponsor sponsor = (Sponsor) o;
        return Objects.equals(sponsorInfo, sponsor.sponsorInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sponsorInfo);
    }
}
