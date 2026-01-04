package com.koliexpress.tripservice.builder.dto.trip;

import com.koliexpress.tripservice.builder.dto.LocationRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.dto.transport.BusTransportRequestDtoTestBuilder;
import com.koliexpress.tripservice.dto.LocationRequestDto;
import com.koliexpress.tripservice.dto.transport.BusTransportRequestDto;
import com.koliexpress.tripservice.dto.trip.BusTripRequestDto;
import com.koliexpress.tripservice.enums.TransportType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BusTripRequestDtoTestBuilder {
    private UUID travelerId = UUID.randomUUID();
    private LocationRequestDto origin;
    private LocationRequestDto destination;
    private LocalDateTime departureDate = LocalDateTime.now().plusDays(1);
    private LocalDateTime arrivalDate = LocalDateTime.now().plusDays(1).plusHours(5);
    private BigDecimal availableWeight = new BigDecimal("20.00");
    private BigDecimal pricePerKg = new BigDecimal("2.50");
    private BigDecimal priceAsked = new BigDecimal("50.00");
    private TransportType transportType = TransportType.BUS;
    private String notice = "Flexible with pickup/dropoff times";
    private BusTransportRequestDto busDetails;

    private BusTripRequestDtoTestBuilder() {
        // Initialize with default test builders
        this.origin = LocationRequestDtoTestBuilder.aLocationRequestDto()
                .withName("New York Port Authority")
                .withCity("New York")
                .withCountry("USA")
                .withLatitude("40.7561")
                .withLongitude("-73.9900")
                .build();

        this.destination = LocationRequestDtoTestBuilder.aLocationRequestDto()
                .withName("Boston South Station")
                .withCity("Boston")
                .withCountry("USA")
                .withLatitude("42.3520")
                .withLongitude("-71.0552")
                .build();

        this.busDetails = BusTransportRequestDtoTestBuilder.validBusTransport();
    }

    public static BusTripRequestDtoTestBuilder aBusTripRequestDto() {
        return new BusTripRequestDtoTestBuilder();
    }

    public BusTripRequestDtoTestBuilder withTravelerId(UUID travelerId) {
        this.travelerId = travelerId;
        return this;
    }

    public BusTripRequestDtoTestBuilder withOrigin(LocationRequestDto origin) {
        this.origin = origin;
        return this;
    }

    public BusTripRequestDtoTestBuilder withDestination(LocationRequestDto destination) {
        this.destination = destination;
        return this;
    }

    public BusTripRequestDtoTestBuilder withDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public BusTripRequestDtoTestBuilder withArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public BusTripRequestDtoTestBuilder withAvailableWeight(BigDecimal availableWeight) {
        this.availableWeight = availableWeight;
        return this;
    }

    public BusTripRequestDtoTestBuilder withPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
        return this;
    }

    public BusTripRequestDtoTestBuilder withPriceAsked(BigDecimal priceAsked) {
        this.priceAsked = priceAsked;
        return this;
    }

    public BusTripRequestDtoTestBuilder withTransportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public BusTripRequestDtoTestBuilder withNotice(String notice) {
        this.notice = notice;
        return this;
    }

    public BusTripRequestDtoTestBuilder withBusDetails(BusTransportRequestDto busDetails) {
        this.busDetails = busDetails;
        return this;
    }

    // Convenience methods for common values
    public BusTripRequestDtoTestBuilder withAvailableWeight(String availableWeight) {
        this.availableWeight = new BigDecimal(availableWeight);
        return this;
    }

    public BusTripRequestDtoTestBuilder withPricePerKg(String pricePerKg) {
        this.pricePerKg = new BigDecimal(pricePerKg);
        return this;
    }

    public BusTripRequestDtoTestBuilder withPriceAsked(String priceAsked) {
        this.priceAsked = new BigDecimal(priceAsked);
        return this;
    }

    public BusTripRequestDto build() {
        return BusTripRequestDto.builder()
                .travelerId(travelerId)
                .origin(origin)
                .destination(destination)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .availableWeight(availableWeight)
                .pricePerKg(pricePerKg)
                .priceAsked(priceAsked)
                //.transportType(transportType)
                .notice(notice)
                .busDetails(busDetails)
                .build();
    }

    // Convenience methods for common test scenarios
    public static BusTripRequestDto validBusTrip() {
        return aBusTripRequestDto().build();
    }

    public static BusTripRequestDto withNullBusDetails() {
        return aBusTripRequestDto()
                .withBusDetails(null)
                .build();
    }

    public static BusTripRequestDto withInvalidDates() {
        return aBusTripRequestDto()
                .withDepartureDate(LocalDateTime.now().plusDays(2))
                .withArrivalDate(LocalDateTime.now().plusDays(1))  // Arrival before departure
                .build();
    }

    public static BusTripRequestDto withPastDepartureDate() {
        return aBusTripRequestDto()
                .withDepartureDate(LocalDateTime.now().minusDays(1))  // Past date
                .build();
    }

    public static BusTripRequestDto withNullOrigin() {
        return aBusTripRequestDto()
                .withOrigin(null)
                .build();
    }

    public static BusTripRequestDto withNullDestination() {
        return aBusTripRequestDto()
                .withDestination(null)
                .build();
    }

    public static BusTripRequestDto withZeroAvailableWeight() {
        return aBusTripRequestDto()
                .withAvailableWeight(BigDecimal.ZERO)  // Should fail @Positive validation
                .build();
    }

    public static BusTripRequestDto withNegativePrice() {
        return aBusTripRequestDto()
                .withPricePerKg(new BigDecimal("-1.00"))  // Should fail @Positive validation
                .build();
    }

    public static BusTripRequestDto withNullTravelerId() {
        return aBusTripRequestDto()
                .withTravelerId(null)
                .build();
    }

    public static BusTripRequestDto withNullTransportType() {
        return aBusTripRequestDto()
                .withTransportType(null)
                .build();
    }

    // Real-world bus trip scenarios
    public static BusTripRequestDto megabusTrip() {
        return aBusTripRequestDto()
                .withTravelerId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Philadelphia 30th Street Station")
                        .withCity("Philadelphia")
                        .withCountry("USA")
                        .withLatitude("39.9555")
                        .withLongitude("-75.1819")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Washington Union Station")
                        .withCity("Washington")
                        .withCountry("USA")
                        .withLatitude("38.8977")
                        .withLongitude("-77.0065")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(2).withHour(9).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(2).withHour(14).withMinute(30))
                .withAvailableWeight(new BigDecimal("25.00"))
                .withPricePerKg(new BigDecimal("1.75"))
                .withPriceAsked(new BigDecimal("43.75"))
                .withNotice("Economical Megabus journey with Wi-Fi. Can store luggage in overhead bins.")
                .withBusDetails(BusTransportRequestDtoTestBuilder.megabusService())
                .build();
    }

    public static BusTripRequestDto flixbusTrip() {
        return aBusTripRequestDto()
                .withTravelerId(UUID.fromString("223e4567-e89b-12d3-a456-426614174001"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Los Angeles Downtown Station")
                        .withCity("Los Angeles")
                        .withCountry("USA")
                        .withLatitude("34.0522")
                        .withLongitude("-118.2437")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("San Francisco Caltrain Station")
                        .withCity("San Francisco")
                        .withCountry("USA")
                        .withLatitude("37.7749")
                        .withLongitude("-122.4194")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(3).withHour(8).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(3).withHour(18).withMinute(0))
                .withAvailableWeight(new BigDecimal("30.00"))
                .withPricePerKg(new BigDecimal("3.00"))
                .withPriceAsked(new BigDecimal("90.00"))
                .withNotice("Scenic California coast bus journey. Under-seat storage available.")
                .withBusDetails(BusTransportRequestDtoTestBuilder.flixbusService())
                .build();
    }

    public static BusTripRequestDto internationalBusTrip() {
        return aBusTripRequestDto()
                .withTravelerId(UUID.fromString("323e4567-e89b-12d3-a456-426614174002"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Paris Gallieni")
                        .withCity("Paris")
                        .withCountry("France")
                        .withLatitude("48.8566")
                        .withLongitude("2.3522")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("London Victoria Coach Station")
                        .withCity("London")
                        .withCountry("United Kingdom")
                        .withLatitude("51.5074")
                        .withLongitude("-0.1278")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(4).withHour(10).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(4).withHour(19).withMinute(0))
                .withAvailableWeight(new BigDecimal("15.00"))
                .withPricePerKg(new BigDecimal("4.50"))
                .withPriceAsked(new BigDecimal("67.50"))
                .withNotice("Cross-channel international bus service. Passport required for border crossing.")
                .withTransportType(TransportType.BUS)
                .withBusDetails(BusTransportRequestDtoTestBuilder.europeanBus())
                .build();
    }

    public static BusTripRequestDto airportShuttleTrip() {
        return aBusTripRequestDto()
                .withTravelerId(UUID.fromString("423e4567-e89b-12d3-a456-426614174003"))
                .withAvailableWeight(new BigDecimal("10.00"))
                .withPricePerKg(new BigDecimal("5.00"))
                .withPriceAsked(new BigDecimal("50.00"))
                .withDepartureDate(LocalDateTime.now().plusHours(2))
                .withArrivalDate(LocalDateTime.now().plusHours(3))
                .withNotice("Airport shuttle service with luggage compartment.")
                .withTransportType(TransportType.BUS)
                .build();
    }

    public static BusTripRequestDto localTransitTrip() {
        return aBusTripRequestDto()
                .withTravelerId(UUID.fromString("523e4567-e89b-12d3-a456-426614174004"))
                .withAvailableWeight(new BigDecimal("5.00"))
                .withPricePerKg(new BigDecimal("1.00"))
                .withPriceAsked(new BigDecimal("5.00"))
                .withDepartureDate(LocalDateTime.now().plusMinutes(30))
                .withArrivalDate(LocalDateTime.now().plusHours(1))
                .withNotice("Local city bus transit. Small packages only.")
                .withTransportType(TransportType.BUS)
                .build();
    }

    public static BusTripRequestDto longDistanceBusTrip() {
        return aBusTripRequestDto()
                .withTravelerId(UUID.fromString("623e4567-e89b-12d3-a456-426614174005"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Chicago Bus Station")
                        .withCity("Chicago")
                        .withCountry("USA")
                        .withLatitude("41.8781")
                        .withLongitude("-87.6298")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Denver Bus Terminal")
                        .withCity("Denver")
                        .withCountry("USA")
                        .withLatitude("39.7392")
                        .withLongitude("-104.9903")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(5).withHour(20).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(6).withHour(14).withMinute(0))
                .withAvailableWeight(new BigDecimal("40.00"))
                .withPricePerKg(new BigDecimal("2.00"))
                .withPriceAsked(new BigDecimal("80.00"))
                .withNotice("Overnight bus trip with luggage storage in cargo hold.")
                .build();
    }
}