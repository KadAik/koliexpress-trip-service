package com.koliexpress.tripservice.builder.dto.trip;

import com.koliexpress.tripservice.builder.dto.transport.FlightTransportRequestDtoTestBuilder;
import com.koliexpress.tripservice.dto.LocationRequestDTO;
import com.koliexpress.tripservice.dto.LocationRequestDtoTestBuilder;
import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
import com.koliexpress.tripservice.dto.trip.FlightTripRequestDTO;
import com.koliexpress.tripservice.enums.TransportType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class FlightTripRequestDtoTestBuilder {
    private UUID travelerId = UUID.randomUUID();
    private LocationRequestDTO origin;
    private LocationRequestDTO destination;
    private LocalDateTime departureDate = LocalDateTime.now().plusDays(1);
    private LocalDateTime arrivalDate = LocalDateTime.now().plusDays(1).plusHours(6);
    private BigDecimal availableWeight = new BigDecimal("23.00");
    private BigDecimal pricePerKg = new BigDecimal("3.50");
    private BigDecimal priceAsked = new BigDecimal("80.50");
    private TransportType transportType = TransportType.PLANE;
    private String notice = "Carry-on size restrictions apply";
    private FlightTransportRequestDTO flightDetails;

    private FlightTripRequestDtoTestBuilder() {
        // Initialize with default test builders
        this.origin = LocationRequestDtoTestBuilder.aLocationRequestDto()
                .withName("John F. Kennedy International Airport")
                .withCity("New York")
                .withCountry("USA")
                .withLatitude("40.6413")
                .withLongitude("-73.7781")
                .build();

        this.destination = LocationRequestDtoTestBuilder.aLocationRequestDto()
                .withName("Los Angeles International Airport")
                .withCity("Los Angeles")
                .withCountry("USA")
                .withLatitude("33.9416")
                .withLongitude("-118.4085")
                .build();

        this.flightDetails = FlightTransportRequestDtoTestBuilder.validFlightTransport();
    }

    public static FlightTripRequestDtoTestBuilder aFlightTripRequestDto() {
        return new FlightTripRequestDtoTestBuilder();
    }

    public FlightTripRequestDtoTestBuilder withTravelerId(UUID travelerId) {
        this.travelerId = travelerId;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withOrigin(LocationRequestDTO origin) {
        this.origin = origin;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withDestination(LocationRequestDTO destination) {
        this.destination = destination;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withAvailableWeight(BigDecimal availableWeight) {
        this.availableWeight = availableWeight;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withPricePerKg(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withPriceAsked(BigDecimal priceAsked) {
        this.priceAsked = priceAsked;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withTransportType(TransportType transportType) {
        this.transportType = transportType;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withNotice(String notice) {
        this.notice = notice;
        return this;
    }

    public FlightTripRequestDtoTestBuilder withFlightDetails(FlightTransportRequestDTO flightDetails) {
        this.flightDetails = flightDetails;
        return this;
    }

    // Convenience methods for common values
    public FlightTripRequestDtoTestBuilder withAvailableWeight(String availableWeight) {
        this.availableWeight = new BigDecimal(availableWeight);
        return this;
    }

    public FlightTripRequestDtoTestBuilder withPricePerKg(String pricePerKg) {
        this.pricePerKg = new BigDecimal(pricePerKg);
        return this;
    }

    public FlightTripRequestDtoTestBuilder withPriceAsked(String priceAsked) {
        this.priceAsked = new BigDecimal(priceAsked);
        return this;
    }

    public FlightTripRequestDTO build() {
        return FlightTripRequestDTO.builder()
                .travelerId(travelerId)
                .origin(origin)
                .destination(destination)
                .departureDate(departureDate)
                .arrivalDate(arrivalDate)
                .availableWeight(availableWeight)
                .pricePerKg(pricePerKg)
                .priceAsked(priceAsked)
                .transportType(transportType)
                .notice(notice)
                .flightDetails(flightDetails)
                .build();
    }

    // Convenience methods for common test scenarios
    public static FlightTripRequestDTO validFlightTrip() {
        return aFlightTripRequestDto().build();
    }

    public static FlightTripRequestDTO withNullFlightDetails() {
        return aFlightTripRequestDto()
                .withFlightDetails(null)
                .build();
    }

    public static FlightTripRequestDTO withInvalidDates() {
        return aFlightTripRequestDto()
                .withDepartureDate(LocalDateTime.now().plusDays(2))
                .withArrivalDate(LocalDateTime.now().plusDays(1))  // Arrival before departure
                .build();
    }

    public static FlightTripRequestDTO withPastDepartureDate() {
        return aFlightTripRequestDto()
                .withDepartureDate(LocalDateTime.now().minusHours(1))  // Past date
                .build();
    }

    public static FlightTripRequestDTO withZeroAvailableWeight() {
        return aFlightTripRequestDto()
                .withAvailableWeight(BigDecimal.ZERO)  // Should fail @Positive validation
                .build();
    }

    public static FlightTripRequestDTO withNegativePrice() {
        return aFlightTripRequestDto()
                .withPricePerKg(new BigDecimal("-1.00"))  // Should fail @Positive validation
                .build();
    }

    public static FlightTripRequestDTO withNullTravelerId() {
        return aFlightTripRequestDto()
                .withTravelerId(null)
                .build();
    }

    // Real-world flight trip scenarios
    public static FlightTripRequestDTO domesticFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("723e4567-e89b-12d3-a456-426614174006"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Hartsfield-Jackson Atlanta International Airport")
                        .withCity("Atlanta")
                        .withCountry("USA")
                        .withLatitude("33.6407")
                        .withLongitude("-84.4277")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Dallas/Fort Worth International Airport")
                        .withCity("Dallas")
                        .withCountry("USA")
                        .withLatitude("32.8998")
                        .withLongitude("-97.0403")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(2).withHour(14).withMinute(30))
                .withArrivalDate(LocalDateTime.now().plusDays(2).withHour(16).withMinute(45))
                .withAvailableWeight(new BigDecimal("25.00"))
                .withPricePerKg(new BigDecimal("4.00"))
                .withPriceAsked(new BigDecimal("100.00"))
                .withNotice("Domestic flight with checked baggage allowance. Must meet weight restrictions.")
                .withFlightDetails(FlightTransportRequestDtoTestBuilder.domesticFlight())
                .build();
    }

    public static FlightTripRequestDTO internationalFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("823e4567-e89b-12d3-a456-426614174007"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Heathrow Airport")
                        .withCity("London")
                        .withCountry("United Kingdom")
                        .withLatitude("51.4700")
                        .withLongitude("-0.4543")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("John F. Kennedy International Airport")
                        .withCity("New York")
                        .withCountry("USA")
                        .withLatitude("40.6413")
                        .withLongitude("-73.7781")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(3).withHour(18).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(3).withHour(21).withMinute(0))
                .withAvailableWeight(new BigDecimal("30.00"))
                .withPricePerKg(new BigDecimal("5.50"))
                .withPriceAsked(new BigDecimal("165.00"))
                .withNotice("International flight with customs declaration. Extra baggage fees may apply.")
                .withTransportType(TransportType.PLANE)
                .withFlightDetails(FlightTransportRequestDtoTestBuilder.internationalFlight())
                .build();
    }

    public static FlightTripRequestDTO longHaulFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("923e4567-e89b-12d3-a456-426614174008"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Changi Airport")
                        .withCity("Singapore")
                        .withCountry("Singapore")
                        .withLatitude("1.3644")
                        .withLongitude("103.9915")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("San Francisco International Airport")
                        .withCity("San Francisco")
                        .withCountry("USA")
                        .withLatitude("37.6213")
                        .withLongitude("-122.3790")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(4).withHour(23).withMinute(30))
                .withArrivalDate(LocalDateTime.now().plusDays(5).withHour(6).withMinute(0))
                .withAvailableWeight(new BigDecimal("35.00"))
                .withPricePerKg(new BigDecimal("6.00"))
                .withPriceAsked(new BigDecimal("210.00"))
                .withNotice("Long-haul flight with premium economy seating. Weight limit strictly enforced.")
                .withTransportType(TransportType.PLANE)
                .withFlightDetails(FlightTransportRequestDtoTestBuilder.longHaulFlight())
                .build();
    }

    public static FlightTripRequestDTO europeanFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("a23e4567-e89b-12d3-a456-426614174009"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Charles de Gaulle Airport")
                        .withCity("Paris")
                        .withCountry("France")
                        .withLatitude("49.0097")
                        .withLongitude("2.5479")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Amsterdam Airport Schiphol")
                        .withCity("Amsterdam")
                        .withCountry("Netherlands")
                        .withLatitude("52.3086")
                        .withLongitude("4.7639")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(5).withHour(9).withMinute(15))
                .withArrivalDate(LocalDateTime.now().plusDays(5).withHour(10).withMinute(45))
                .withAvailableWeight(new BigDecimal("15.00"))
                .withPricePerKg(new BigDecimal("3.00"))
                .withPriceAsked(new BigDecimal("45.00"))
                .withNotice("Short European flight. Only cabin baggage allowed.")
                .build();
    }

    public static FlightTripRequestDTO cargoFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("b23e4567-e89b-12d3-a456-426614174010"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Memphis International Airport")
                        .withCity("Memphis")
                        .withCountry("USA")
                        .withLatitude("35.0424")
                        .withLongitude("-89.9767")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Hong Kong International Airport")
                        .withCity("Hong Kong")
                        .withCountry("China")
                        .withLatitude("22.3080")
                        .withLongitude("113.9185")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(6).withHour(2).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(6).withHour(22).withMinute(0))
                .withAvailableWeight(new BigDecimal("100.00"))
                .withPricePerKg(new BigDecimal("8.00"))
                .withPriceAsked(new BigDecimal("800.00"))
                .withNotice("Cargo flight with large capacity. Special handling required for fragile items.")
                .withTransportType(TransportType.PLANE)
                .build();
    }

    public static FlightTripRequestDTO redEyeFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("c23e4567-e89b-12d3-a456-426614174011"))
                .withOrigin(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("Los Angeles International Airport")
                        .withCity("Los Angeles")
                        .withCountry("USA")
                        .withLatitude("33.9416")
                        .withLongitude("-118.4085")
                        .build())
                .withDestination(LocationRequestDtoTestBuilder.aLocationRequestDto()
                        .withName("John F. Kennedy International Airport")
                        .withCity("New York")
                        .withCountry("USA")
                        .withLatitude("40.6413")
                        .withLongitude("-73.7781")
                        .build())
                .withDepartureDate(LocalDateTime.now().plusDays(7).withHour(23).withMinute(0))
                .withArrivalDate(LocalDateTime.now().plusDays(8).withHour(7).withMinute(30))
                .withAvailableWeight(new BigDecimal("20.00"))
                .withPricePerKg(new BigDecimal("3.75"))
                .withPriceAsked(new BigDecimal("75.00"))
                .withNotice("Red-eye flight. Quiet cabin expected.")
                .withFlightDetails(FlightTransportRequestDtoTestBuilder.aFlightTransportRequestDto()
                        .withFlightNumber("UA234")
                        .withAirlineCode("UA")
                        .withAircraftType("Boeing 757")
                        .withDepartureAirportCode("LAX")
                        .withArrivalAirportCode("JFK")
                        .build())
                .build();
    }

    public static FlightTripRequestDTO businessClassFlightTrip() {
        return aFlightTripRequestDto()
                .withTravelerId(UUID.fromString("d23e4567-e89b-12d3-a456-426614174012"))
                .withAvailableWeight(new BigDecimal("40.00"))
                .withPricePerKg(new BigDecimal("10.00"))
                .withPriceAsked(new BigDecimal("400.00"))
                .withNotice("Business class flight with extra baggage allowance and priority handling.")
                .withTransportType(TransportType.PLANE)
                .withFlightDetails(FlightTransportRequestDtoTestBuilder.aFlightTransportRequestDto()
                        .withProviderName("Emirates First Class")
                        .withFlightNumber("EK202")
                        .withAirlineCode("EK")
                        .withAircraftType("Airbus A380")
                        .withDepartureAirportCode("DXB")
                        .withArrivalAirportCode("LHR")
                        .build())
                .build();
    }
}