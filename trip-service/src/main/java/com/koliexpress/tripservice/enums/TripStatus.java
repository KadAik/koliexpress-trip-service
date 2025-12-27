package com.koliexpress.tripservice.enums;

import lombok.Getter;

@Getter
public enum TripStatus {
    DRAFT("DRAFT"),
    PUBLISHED("PUBLISHED"),
    AVAILABLE("AVAILABLE"),
    MATCHED("MATCHED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED"),
    EXPIRED("EXPIRED"),
    SUSPENDED("SUSPENDED");

    private final String value;

    TripStatus(String value) {
        this.value = value;
    }

}
