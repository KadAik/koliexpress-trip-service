package com.koliexpress.tripservice.modelbuilder;

import com.koliexpress.tripservice.valueobjects.Location;

import java.math.BigDecimal;

public class LocationTestBuilder {

    private String name = "Default Location";
    private String city = "Default City";
    private String country = "Default Country";
    private BigDecimal latitude = BigDecimal.valueOf(23.780573);
    private BigDecimal longitude = BigDecimal.valueOf(90.279239);

    public static LocationTestBuilder builder(){
        return new LocationTestBuilder();
    }

    public static LocationTestBuilder builder(String name, String city, String country, double latitude, double longitude){
        return new LocationTestBuilder(name, city, country, latitude, longitude);
    }

    private LocationTestBuilder() {}

    public LocationTestBuilder name(String name){
        this.name = name;
        return this;
    }
    public LocationTestBuilder city(String city){
        this.city = city;
        return this;
    }
    public LocationTestBuilder country(String country){
        this.country = country;
        return this;
    }
    public LocationTestBuilder latitude(double latitude){
        this.latitude = BigDecimal.valueOf(latitude);
        return this;
    }
    public LocationTestBuilder longitude(double longitude){
        this.longitude = BigDecimal.valueOf(longitude);
        return this;
    }

    private LocationTestBuilder(
            String name,
            String city,
            String country,
            double latitude, double longitude
    )
    {
        this.name = name;
        this.city = city;
        this.country = country;
        this.latitude = BigDecimal.valueOf(latitude);
        this.longitude = BigDecimal.valueOf(longitude);
    }

    // ========== Predefined locations ==========

    public static Location cotonou() {
        return builder(
                "Cadjehoun",
                "Cotonou",
                "Benin",
                6.357,
                2.384
        ).build();
    }

    public static Location lagos() {
        return builder(
                "Murtala Muhammed Airport",
                "Lagos",
                "Nigeria",
                6.577,
                3.321
        ).build();
    }

    public static Location accra() {
        return builder(
                "Kotoka International Airport",
                "Accra",
                "Ghana",
                5.605,
                -0.167
        ).build();
    }

    public static Location abidjan() {
        return builder(
                "Félix-Houphouët-Boigny Airport",
                "Abidjan",
                "Côte d'Ivoire",
                5.261,
                -3.926
        ).build();
    }

    public static Location dakar() {
        return builder(
                "Blaise Diagne International Airport",
                "Dakar",
                "Senegal",
                14.670,
                -17.073
        ).build();
    }

    // ========== Factory Methods ==========

    public static Location aLocation(String name, String city, String country, double latitude, double longitude) {
        return builder(
                name,
                city,
                country,
               latitude,
               longitude
        ).build();
    }

    public static Location aDefaultLocation() {
        return new LocationTestBuilder().build();
    }

    public Location build() {
        return new Location(name, city, country, latitude, longitude);
    }
}