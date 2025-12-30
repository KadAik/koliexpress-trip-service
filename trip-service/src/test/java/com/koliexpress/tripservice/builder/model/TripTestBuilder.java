package com.koliexpress.tripservice.builder.model;

import com.koliexpress.tripservice.builder.model.transport.BusTransportTestBuilder;
import com.koliexpress.tripservice.builder.model.transport.CarTransportTestBuilder;
import com.koliexpress.tripservice.builder.model.transport.FlightTransportTestBuilder;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TripStatus;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.model.transport.Transport;
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
    private Location origin = LocationTestBuilder.cotonou().build();
    private Location destination = LocationTestBuilder.lagos().build();
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
     * Create a in progress Trip
     */
    public static TripTestBuilder aCompletedTrip() {
        return new TripTestBuilder()
                .withStatus(TripStatus.IN_PROGRESS)
                .withDepartureDate(LocalDateTime.now().minusHours(1))
                .withArrivalDate(LocalDateTime.now().plusHours(1));
    }

    /**
     * Create a Canceled Trip
     */
    public static TripTestBuilder aCancelledTrip() {
        return new TripTestBuilder()
                .withStatus(TripStatus.CANCELLED);
    }

    /**
     * Create a Trip from Cotonou to Lagos
     */
    public static TripTestBuilder aCotonouToLagosTrip() {
        return new TripTestBuilder()
                .withOrigin(LocationTestBuilder.cotonou().build())
                .withDestination(LocationTestBuilder.lagos().build());
    }

    /**
     * Create an International Flight Trip
     */
    public static TripTestBuilder anInternationalFlightTrip() {
        return new TripTestBuilder()
                .withOrigin(LocationTestBuilder.london().build())
                .withDestination(LocationTestBuilder.newYork().build())
                .withTransportType(TransportType.PLANE)
                .withTransport(FlightTransportTestBuilder.aSingaporeAirlinesFlight().build())
                .withAvailableWeight(30)
                .withPricePerKg(15.00);
    }

    /**
     * Create a Long-Distance Bus Trip
     */
    public static TripTestBuilder aLongDistanceBusTrip() {
        return new TripTestBuilder()
                .withOrigin(LocationTestBuilder.lagos().build())
                .withDestination(LocationTestBuilder.abuja().build())
                .withTransportType(TransportType.BUS)
                .withTransport(BusTransportTestBuilder.aBus().longDistanceBus().build())
                .withAvailableWeight(20)
                .withPricePerKg(5.00);
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

    public TripTestBuilder withAvailableWeight(String weight) {
        this.availableWeight = new BigDecimal(weight);
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

    public TripTestBuilder withPricePerKg(String price) {
        this.pricePerKg = new BigDecimal(price);
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

    public TripTestBuilder withPriceAsked(String price) {
        this.priceAsked = new BigDecimal(price);
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

    public TripTestBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TripTestBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    // ========== Convenience Methods ==========

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

    public TripTestBuilder departingToday() {
        this.departureDate = LocalDateTime.now().plusHours(2);
        this.arrivalDate = this.departureDate.plusHours(3);
        return this;
    }

    public TripTestBuilder departingInHours(int hours) {
        this.departureDate = LocalDateTime.now().plusHours(hours);
        this.arrivalDate = this.departureDate.plusHours(2);
        return this;
    }

    public TripTestBuilder withPastDeparture() {
        this.departureDate = LocalDateTime.now().minusDays(1);
        this.arrivalDate = this.departureDate.plusHours(2);
        return this;
    }

    public TripTestBuilder withDurationHours(int hours) {
        this.arrivalDate = this.departureDate.plusHours(hours);
        return this;
    }

    public TripTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    public TripTestBuilder withoutTransport() {
        this.transport = null;
        return this;
    }

    public TripTestBuilder withoutNotice() {
        this.notice = null;
        return this;
    }

    public TripTestBuilder withZeroAvailableWeight() {
        this.availableWeight = BigDecimal.ZERO;
        return this;
    }

    public TripTestBuilder withNegativePrice() {
        this.pricePerKg = BigDecimal.valueOf(-5.00);
        this.priceAsked = BigDecimal.valueOf(-50.00);
        return this;
    }

    public TripTestBuilder recentlyCreated() {
        this.createdAt = LocalDateTime.now().minusMinutes(30);
        this.updatedAt = LocalDateTime.now().minusMinutes(30);
        return this;
    }

    public TripTestBuilder createdDaysAgo(int days) {
        this.createdAt = LocalDateTime.now().minusDays(days);
        this.updatedAt = LocalDateTime.now().minusDays(days);
        return this;
    }

    // ========== Status Convenience Methods ==========

    public TripTestBuilder asDraft() {
        this.status = TripStatus.DRAFT;
        return this;
    }

    public TripTestBuilder asAvailable() {
        this.status = TripStatus.AVAILABLE;
        return this;
    }

    public TripTestBuilder asInProgress() {
        this.status = TripStatus.IN_PROGRESS;
        return this;
    }

    public TripTestBuilder asCompleted() {
        this.status = TripStatus.COMPLETED;
        return this;
    }

    public TripTestBuilder asCanceled() {
        this.status = TripStatus.CANCELLED;
        return this;
    }

    public TripTestBuilder asExpired() {
        this.status = TripStatus.EXPIRED;
        return this;
    }

    // ========== Pre-built Trip Instances ==========

    public static Trip defaultTripInstance() {
        return aDefaultTrip().build();
    }

    public static Trip availableFlightTripInstance() {
        return aFlightTrip().asAvailable().build();
    }

    public static Trip completedCarTripInstance() {
        return aCarTrip().asCompleted().build();
    }

    public static Trip internationalFlightTripInstance() {
        return anInternationalFlightTrip().build();
    }

    public static Trip longDistanceBusTripInstance() {
        return aLongDistanceBusTrip().build();
    }

    public static Trip tomorrowsFlightTrip() {
        return aFlightTrip().departingTomorrow().build();
    }

    public static Trip tripWithoutId() {
        return aDefaultTrip().withoutId().build();
    }

    public static Trip tripWithoutTransport() {
        return aDefaultTrip().withoutTransport().build();
    }

    public static Trip tripWithZeroWeight() {
        return aDefaultTrip().withZeroAvailableWeight().build();
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