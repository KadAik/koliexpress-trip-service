package com.koliexpress.tripservice.enums;

import lombok.Getter;

@Getter
public enum TransportType {
    PLANE("Plane"),
    CAR("Car"),
    BUS("Bus"),
    BOAT("Boat"),
    BICYCLE("Bicycle"),
    MOTORCYCLE("Motorcycle"),
    TRUCK("Truck");

    private final String value;

    private TransportType(String value) {
        this.value = value;
    }
}
