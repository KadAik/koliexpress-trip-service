package com.koliexpress.tripservice.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Embeddable
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED) // JPA only
public class Location {

    private String name;
    private String city;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Location(String name, String city, String country, BigDecimal latitude, BigDecimal longitude) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (city == null || city.isBlank()) throw new IllegalArgumentException("City required");
        if (country == null || country.isBlank()) throw new IllegalArgumentException("Country required");
        if (latitude.compareTo(BigDecimal.valueOf(-90)) < 0 || latitude.compareTo(BigDecimal.valueOf(90)) > 0) 
            throw new IllegalArgumentException("Latitude out of range");
        if (longitude.compareTo(BigDecimal.valueOf(-180)) < 0 || longitude.compareTo(BigDecimal.valueOf(180)) > 0) 
            throw new IllegalArgumentException("Longitude out of range");

        this.name = name;
        this.city = normalizeCity(city);
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Location other)) return false;

        return latitude.compareTo(other.latitude) == 0
                && longitude.compareTo(other.longitude) == 0
                && Objects.equals(name, other.name)
                && Objects.equals(city, other.city)
                && Objects.equals(country, other.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city, country, latitude, longitude);
    }

    private static String normalizeCity(String city) {
        if (city == null || city.isBlank()) return city;
        return Arrays.stream(city.trim().replaceAll("\\s+", " ").split(" "))
                .map(word -> word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
