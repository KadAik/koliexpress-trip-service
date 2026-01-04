package com.koliexpress.tripservice.builder.dto;

import com.koliexpress.tripservice.dto.LocationRequestDto;

import java.math.BigDecimal;

public class LocationRequestDtoTestBuilder {
    private String name = "Central Park";
    private String city = "New York";
    private String country = "USA";
    private BigDecimal latitude = new BigDecimal("40.7829");
    private BigDecimal longitude = new BigDecimal("-73.9654");

    private LocationRequestDtoTestBuilder() {
    }

    public static LocationRequestDtoTestBuilder aLocationRequestDto() {
        return new LocationRequestDtoTestBuilder();
    }

    public LocationRequestDtoTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LocationRequestDtoTestBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public LocationRequestDtoTestBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public LocationRequestDtoTestBuilder withLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationRequestDtoTestBuilder withLatitude(String latitude) {
        this.latitude = new BigDecimal(latitude);
        return this;
    }

    public LocationRequestDtoTestBuilder withLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationRequestDtoTestBuilder withLongitude(String longitude) {
        this.longitude = new BigDecimal(longitude);
        return this;
    }

    public LocationRequestDto build() {
        return new LocationRequestDto(name, city, country, latitude, longitude);
    }

    // Convenience methods for test scenarios
    public static LocationRequestDto validLocation() {
        return aLocationRequestDto().build();
    }

    public static LocationRequestDto withInvalidLatitude() {
        return aLocationRequestDto()
                .withLatitude(new BigDecimal("91.0"))  // Exceeds @Max(90)
                .build();
    }

    public static LocationRequestDto withInvalidLongitude() {
        return aLocationRequestDto()
                .withLongitude(new BigDecimal("181.0"))  // Exceeds @Max(180)
                .build();
    }

    public static LocationRequestDto withNullName() {
        return aLocationRequestDto()
                .withName(null)
                .build();
    }

    public static LocationRequestDto withEmptyCity() {
        return aLocationRequestDto()
                .withCity("")
                .build();
    }

    public static LocationRequestDto withEmptyCountry() {
        return aLocationRequestDto()
                .withCountry(" ")
                .build();  // Just whitespace
    }

    public static LocationRequestDto londonLocation() {
        return aLocationRequestDto()
                .withName("London Eye")
                .withCity("London")
                .withCountry("United Kingdom")
                .withLatitude("51.5033")
                .withLongitude("-0.1195")
                .build();
    }

    public static LocationRequestDto tokyoLocation() {
        return aLocationRequestDto()
                .withName("Tokyo Tower")
                .withCity("Tokyo")
                .withCountry("Japan")
                .withLatitude("35.6586")
                .withLongitude("139.7454")
                .build();
    }
}