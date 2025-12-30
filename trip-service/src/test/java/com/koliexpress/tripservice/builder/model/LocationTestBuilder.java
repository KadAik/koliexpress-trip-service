package com.koliexpress.tripservice.builder.model;

import com.koliexpress.tripservice.valueobjects.Location;

import java.math.BigDecimal;

public class LocationTestBuilder {

    private String name = "Default Location";
    private String city = "Default City";
    private String country = "Default Country";
    private BigDecimal latitude = BigDecimal.valueOf(23.780573);
    private BigDecimal longitude = BigDecimal.valueOf(90.279239);

    // ========== Factory Methods ==========

    public static LocationTestBuilder aLocation() {
        return new LocationTestBuilder();
    }

    public static LocationTestBuilder withCoordinates(String name, String city, String country, double latitude, double longitude) {
        return new LocationTestBuilder()
                .withName(name)
                .withCity(city)
                .withCountry(country)
                .withLatitude(latitude)
                .withLongitude(longitude);
    }

    // ========== Builder Methods ==========

    public LocationTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LocationTestBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public LocationTestBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public LocationTestBuilder withLatitude(double latitude) {
        this.latitude = BigDecimal.valueOf(latitude);
        return this;
    }

    public LocationTestBuilder withLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public LocationTestBuilder withLatitude(String latitude) {
        this.latitude = new BigDecimal(latitude);
        return this;
    }

    public LocationTestBuilder withLongitude(double longitude) {
        this.longitude = BigDecimal.valueOf(longitude);
        return this;
    }

    public LocationTestBuilder withLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public LocationTestBuilder withLongitude(String longitude) {
        this.longitude = new BigDecimal(longitude);
        return this;
    }

    // ========== Convenience Methods ==========

    public LocationTestBuilder withoutName() {
        this.name = null;
        return this;
    }

    public LocationTestBuilder withoutCity() {
        this.city = null;
        return this;
    }

    public LocationTestBuilder withoutCountry() {
        this.country = null;
        return this;
    }

    public LocationTestBuilder withInvalidLatitude() {
        this.latitude = BigDecimal.valueOf(91.0); // Exceeds valid range
        return this;
    }

    public LocationTestBuilder withInvalidLongitude() {
        this.longitude = BigDecimal.valueOf(181.0); // Exceeds valid range
        return this;
    }

    public LocationTestBuilder withNegativeLatitude() {
        this.latitude = BigDecimal.valueOf(-91.0); // Below valid range
        return this;
    }

    public LocationTestBuilder withNegativeLongitude() {
        this.longitude = BigDecimal.valueOf(-181.0); // Below valid range
        return this;
    }

    public LocationTestBuilder withZeroCoordinates() {
        this.latitude = BigDecimal.ZERO;
        this.longitude = BigDecimal.ZERO;
        return this;
    }

    // ========== Predefined Locations (African) ==========

    public static LocationTestBuilder cotonou() {
        return aLocation()
                .withName("Cadjehoun")
                .withCity("Cotonou")
                .withCountry("Benin")
                .withLatitude(6.357)
                .withLongitude(2.384);
    }

    public static LocationTestBuilder lagos() {
        return aLocation()
                .withName("Murtala Muhammed Airport")
                .withCity("Lagos")
                .withCountry("Nigeria")
                .withLatitude(6.577)
                .withLongitude(3.321);
    }

    public static LocationTestBuilder abuja() {
        return aLocation()
                .withName("Nnamdi Azikiwe International Airport")
                .withCity("Abuja")
                .withCountry("Nigeria")
                .withLatitude(9.008)
                .withLongitude(7.263);
    }

    public static LocationTestBuilder accra() {
        return aLocation()
                .withName("Kotoka International Airport")
                .withCity("Accra")
                .withCountry("Ghana")
                .withLatitude(5.605)
                .withLongitude(-0.167);
    }

    public static LocationTestBuilder abidjan() {
        return aLocation()
                .withName("Félix-Houphouët-Boigny Airport")
                .withCity("Abidjan")
                .withCountry("Côte d'Ivoire")
                .withLatitude(5.261)
                .withLongitude(-3.926);
    }

    public static LocationTestBuilder dakar() {
        return aLocation()
                .withName("Blaise Diagne International Airport")
                .withCity("Dakar")
                .withCountry("Senegal")
                .withLatitude(14.670)
                .withLongitude(-17.073);
    }

    public static LocationTestBuilder nairobi() {
        return aLocation()
                .withName("Jomo Kenyatta International Airport")
                .withCity("Nairobi")
                .withCountry("Kenya")
                .withLatitude(-1.319)
                .withLongitude(36.928);
    }

    public static LocationTestBuilder cairo() {
        return aLocation()
                .withName("Cairo International Airport")
                .withCity("Cairo")
                .withCountry("Egypt")
                .withLatitude(30.122)
                .withLongitude(31.406);
    }

    public static LocationTestBuilder johannesburg() {
        return aLocation()
                .withName("O.R. Tambo International Airport")
                .withCity("Johannesburg")
                .withCountry("South Africa")
                .withLatitude(-26.136)
                .withLongitude(28.230);
    }

    public static LocationTestBuilder casablanca() {
        return aLocation()
                .withName("Mohammed V International Airport")
                .withCity("Casablanca")
                .withCountry("Morocco")
                .withLatitude(33.367)
                .withLongitude(-7.590);
    }

    // ========== Predefined Locations (International) ==========

    public static LocationTestBuilder paris() {
        return aLocation()
                .withName("Charles de Gaulle Airport")
                .withCity("Paris")
                .withCountry("France")
                .withLatitude(49.009)
                .withLongitude(2.548);
    }

    public static LocationTestBuilder london() {
        return aLocation()
                .withName("Heathrow Airport")
                .withCity("London")
                .withCountry("United Kingdom")
                .withLatitude(51.470)
                .withLongitude(-0.454);
    }

    public static LocationTestBuilder newYork() {
        return aLocation()
                .withName("John F. Kennedy International Airport")
                .withCity("New York")
                .withCountry("USA")
                .withLatitude(40.641)
                .withLongitude(-73.778);
    }

    public static LocationTestBuilder dubai() {
        return aLocation()
                .withName("Dubai International Airport")
                .withCity("Dubai")
                .withCountry("UAE")
                .withLatitude(25.253)
                .withLongitude(55.364);
    }

    public static LocationTestBuilder tokyo() {
        return aLocation()
                .withName("Narita International Airport")
                .withCity("Tokyo")
                .withCountry("Japan")
                .withLatitude(35.772)
                .withLongitude(140.387);
    }

    public static LocationTestBuilder sydney() {
        return aLocation()
                .withName("Sydney Kingsford Smith Airport")
                .withCity("Sydney")
                .withCountry("Australia")
                .withLatitude(-33.946)
                .withLongitude(151.177);
    }

    public static LocationTestBuilder singapore() {
        return aLocation()
                .withName("Changi Airport")
                .withCity("Singapore")
                .withCountry("Singapore")
                .withLatitude(1.364)
                .withLongitude(103.991);
    }

    // ========== Predefined Locations (Bus/Train Stations) ==========

    public static LocationTestBuilder lagosBusTerminal() {
        return aLocation()
                .withName("Jibowu Bus Terminal")
                .withCity("Lagos")
                .withCountry("Nigeria")
                .withLatitude(6.525)
                .withLongitude(3.367);
    }

    public LocationTestBuilder abujaBusTerminal() {
        return aLocation()
                .withName("Utako Motor Park")
                .withCity("Abuja")
                .withCountry("Nigeria")
                .withLatitude(9.067)
                .withLongitude(7.483);
    }

    public static LocationTestBuilder parisCoachStation() {
        return aLocation()
                .withName("Paris Gallieni International Bus Station")
                .withCity("Paris")
                .withCountry("France")
                .withLatitude(48.867)
                .withLongitude(2.417);
    }

    public static LocationTestBuilder londonVictoriaStation() {
        return aLocation()
                .withName("Victoria Coach Station")
                .withCity("London")
                .withCountry("United Kingdom")
                .withLatitude(51.493)
                .withLongitude(-0.145);
    }

    // ========== Edge Case Locations ==========

    public static LocationTestBuilder locationWithoutName() {
        return aLocation()
                .withoutName()
                .withCity("Test City")
                .withCountry("Test Country")
                .withLatitude(0.0)
                .withLongitude(0.0);
    }

    public static LocationTestBuilder locationWithInvalidCoordinates() {
        return aLocation()
                .withName("Invalid Location")
                .withCity("Test City")
                .withCountry("Test Country")
                .withInvalidLatitude()
                .withInvalidLongitude();
    }

    public static LocationTestBuilder locationWithZeroCoordinates() {
        return aLocation()
                .withName("Zero Coordinates")
                .withCity("Equator City")
                .withCountry("Prime Meridian Country")
                .withZeroCoordinates();
    }

    public static LocationTestBuilder locationWithNullFields() {
        return aLocation()
                .withoutName()
                .withoutCity()
                .withoutCountry()
                .withLatitude((BigDecimal) null)
                .withLongitude((BigDecimal) null);
    }

    // ========== Pre-built Instance Methods ==========

    public static LocationTestBuilder defaultLocation() {
        return aLocation();
    }

    public static LocationTestBuilder africanAirport() {
        return lagos();
    }

    public static LocationTestBuilder europeanAirport() {
        return paris();
    }

    public static LocationTestBuilder asianAirport() {
        return dubai();
    }

    public static LocationTestBuilder americanAirport() {
        return newYork();
    }

    public static LocationTestBuilder busTerminal() {
        return lagosBusTerminal();
    }

    // ========== Build Method ==========

    public Location build() {
        return new Location(name, city, country, latitude, longitude);
    }
}