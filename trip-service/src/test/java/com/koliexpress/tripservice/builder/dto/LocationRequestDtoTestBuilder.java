package com.koliexpress.tripservice.dto;

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

    public LocationRequestDTO build() {
        return new LocationRequestDTO(name, city, country, latitude, longitude);
    }

    // Convenience methods for test scenarios
    public static LocationRequestDTO validLocation() {
        return aLocationRequestDto().build();
    }

    public static LocationRequestDTO withInvalidLatitude() {
        return aLocationRequestDto()
                .withLatitude(new BigDecimal("91.0"))  // Exceeds @Max(90)
                .build();
    }

    public static LocationRequestDTO withInvalidLongitude() {
        return aLocationRequestDto()
                .withLongitude(new BigDecimal("181.0"))  // Exceeds @Max(180)
                .build();
    }

    public static LocationRequestDTO withNullName() {
        return aLocationRequestDto()
                .withName(null)
                .build();
    }

    public static LocationRequestDTO withEmptyCity() {
        return aLocationRequestDto()
                .withCity("")
                .build();
    }

    public static LocationRequestDTO withEmptyCountry() {
        return aLocationRequestDto()
                .withCountry(" ")
                .build();  // Just whitespace
    }

    public static LocationRequestDTO londonLocation() {
        return aLocationRequestDto()
                .withName("London Eye")
                .withCity("London")
                .withCountry("United Kingdom")
                .withLatitude("51.5033")
                .withLongitude("-0.1195")
                .build();
    }

    public static LocationRequestDTO tokyoLocation() {
        return aLocationRequestDto()
                .withName("Tokyo Tower")
                .withCity("Tokyo")
                .withCountry("Japan")
                .withLatitude("35.6586")
                .withLongitude("139.7454")
                .build();
    }
}