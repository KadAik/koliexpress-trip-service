package com.koliexpress.tripservice.modelbuilder;

import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TripStatus;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.model.transport.*;
import com.koliexpress.tripservice.modelbuilder.transport.BusTransportTestBuilder;
import com.koliexpress.tripservice.modelbuilder.transport.CarTransportTestBuilder;
import com.koliexpress.tripservice.modelbuilder.transport.FlightTransportTestBuilder;
import com.koliexpress.tripservice.valueobjects.Location;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder for creating Trip instances in tests
 * Uses the Builder pattern.
 */
public class TripTestBuilder {

    // Default values
    private UUID id = UUID.randomUUID();
    private Traveler traveler = TravelerTestBuilder.aDefaultTraveler().build();
    private Location origin = LocationTestBuilder.cotonou();
    private Location destination = LocationTestBuilder.lagos();
    private LocalDateTime departureDate = LocalDateTime.now().plusDays(7);
    private LocalDateTime arrivalDate = LocalDateTime.now().plusDays(7).plusHours(2);
    private BigDecimal availableWeight = BigDecimal.valueOf(10);
    private BigDecimal pricePerKg = BigDecimal.valueOf(8.50);
    private BigDecimal priceAsked = BigDecimal.valueOf(85);
    private TransportType transportType = TransportType.PLANE;
    private Transport transport = FlightTransportTestBuilder.aFlight().build();
    private TripStatus status = TripStatus.DRAFT;
    private String notice = "Electronics components, handle with care.";
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    private TripTestBuilder() {}

    // ========== Factory Methods ==========

    /**
     * Create a default Trip (Flight: Cotonou -> Lagos)
     */
    public static TripTestBuilder aDefaultTrip() {
        return new TripTestBuilder();
    }

    /**
     * Create a Flight Trip
     */
    public static TripTestBuilder aFlightTrip() {
        return new TripTestBuilder()
                .withTransportType(TransportType.PLANE)
                .withTransport(FlightTransportTestBuilder.aFlight().build());
    }

    /**
     * Create a Bus Trip
     */
    public static TripTestBuilder aBusTrip() {
        return new TripTestBuilder()
                .withTransportType(TransportType.BUS)
                .withTransport(BusTransportTestBuilder.aBus().build());
    }

    /**
     * Create a Car Trip
     */
    public static TripTestBuilder aCarTrip() {
        return new TripTestBuilder()
                .withTransportType(TransportType.CAR)
                .withTransport(CarTransportTestBuilder.aCar().build());
    }

    /**
     * Create an Available Trip
     */
    public static TripTestBuilder anAvailableTrip() {
        return new TripTestBuilder()
                .withStatus(TripStatus.AVAILABLE)
                .withDepartureDate(LocalDateTime.now().plusDays(3));
    }

    /**
     * Create a Trip from Cotonou to Lagos
     */
    public static TripTestBuilder aCotonouToLagosTrip() {
        return new TripTestBuilder()
                .withOrigin(LocationTestBuilder.cotonou())
                .withDestination(LocationTestBuilder.lagos());
    }

    // ========== Builder Methods ==========

    public TripTestBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public TripTestBuilder withTraveler(Traveler traveler) {
        this.traveler = traveler;
        return this;
    }

    public TripTestBuilder withOrigin(Location origin) {
        this.origin = origin;
        return this;
    }

    public TripTestBuilder withDestination(Location destination) {
        this.destination = destination;
        return this;
    }

    public TripTestBuilder withDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public TripTestBuilder withArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public TripTestBuilder withAvailableWeight(BigDecimal weight) {
        this.availableWeight = weight;
        return this;
    }

    public TripTestBuilder withAvailableWeight(double weight) {
        this.availableWeight = BigDecimal.valueOf(weight);
        return this;
    }

    public TripTestBuilder withPricePerKg(BigDecimal price) {
        this.pricePerKg = price;
        return this;
    }

    public TripTestBuilder withPricePerKg(double price) {
        this.pricePerKg = BigDecimal.valueOf(price);
        return this;
    }

    public TripTestBuilder withPriceAsked(BigDecimal price) {
        this.priceAsked = price;
        return this;
    }

    public TripTestBuilder withPriceAsked(double price) {
        this.priceAsked = BigDecimal.valueOf(price);
        return this;
    }

    public TripTestBuilder withTransportType(TransportType type) {
        this.transportType = type;
        return this;
    }

    public TripTestBuilder withTransport(Transport transport) {
        this.transport = transport;
        return this;
    }

    public TripTestBuilder withStatus(TripStatus status) {
        this.status = status;
        return this;
    }

    public TripTestBuilder withNotice(String notice) {
        this.notice = notice;
        return this;
    }

    public TripTestBuilder departingTomorrow() {
        this.departureDate = LocalDateTime.now().plusDays(1);
        this.arrivalDate = this.departureDate.plusHours(2);
        return this;
    }

    public TripTestBuilder departingNextWeek() {
        this.departureDate = LocalDateTime.now().plusDays(7);
        this.arrivalDate = this.departureDate.plusHours(2);
        return this;
    }

    public TripTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    // ========== Build Method ==========

    public Trip build() {
        Trip trip = new Trip();
        trip.setId(id);
        trip.setTraveler(traveler);
        trip.setOrigin(origin);
        trip.setDestination(destination);
        trip.setDepartureDate(departureDate);
        trip.setArrivalDate(arrivalDate);
        trip.setAvailableWeight(availableWeight);
        trip.setPricePerKg(pricePerKg);
        trip.setPriceAsked(priceAsked);
        trip.setTransportType(transportType);
        trip.setTransport(transport);
        trip.setStatus(status);
        trip.setNotice(notice);
        trip.setCreatedAt(createdAt);
        trip.setUpdatedAt(updatedAt);
        return trip;
    }
}

