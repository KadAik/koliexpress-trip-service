package com.koliexpress.tripservice.builder.dto.trip;

import com.koliexpress.tripservice.dto.LocationRequestDto;
import com.koliexpress.tripservice.dto.trip.CarTripRequestDto;
import com.koliexpress.tripservice.dto.transport.CarTransportRequestDto;
import com.koliexpress.tripservice.enums.TransportType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder for creating CarTripRequestDto instances in tests
 * Uses the Builder pattern.
 */
public class CarTripRequestDtoTestBuilder {

    // Default values
    private UUID travelerId = UUID.randomUUID();
    private LocationRequestDto origin = createLocationRequestDto("Pickup Point", "Cotonou", "Benin", 6.357, 2.384);
    private LocationRequestDto destination = createLocationRequestDto("Drop-off Point", "Lagos", "Nigeria", 6.577, 3.321);
    private LocalDateTime departureDate = LocalDateTime.now().plusDays(7);
    private LocalDateTime arrivalDate = LocalDateTime.now().plusDays(7).plusHours(8);
    private BigDecimal availableWeight = BigDecimal.valueOf(30);
    private BigDecimal pricePerKg = BigDecimal.valueOf(3);
    private BigDecimal priceAsked = BigDecimal.valueOf(90);
    private TransportType transportType = TransportType.CAR;
    private String notice = "Handle packages carefully.";
    private CarTransportRequestDto carDetails = createDefaultCarTransportRequestDto();

    private CarTripRequestDtoTestBuilder() {}

     /**
     * Create a default CarTripRequestDto
     */
    public static CarTripRequestDtoTestBuilder aCarTripRequestDto() {
        return new CarTripRequestDtoTestBuilder();
    }

    /**
     * Create a CarTripRequestDto from Cotonou to Lagos
     */
    public static CarTripRequestDtoTestBuilder aCotonouToLagosCarTripRequest() {
        return new CarTripRequestDtoTestBuilder()
                .withOrigin(createLocationRequestDto("Pickup Point", "Cotonou", "Benin", 6.357, 2.384))
                .withDestination(createLocationRequestDto("Drop-off Point", "Lagos", "Nigeria", 6.577, 3.321));
    }

    /**
     * Create a CarTripRequestDto departing tomorrow
     */
    public static CarTripRequestDtoTestBuilder aCarTripRequestDepartingTomorrow() {
        return new CarTripRequestDtoTestBuilder()
                .departingTomorrow();
    }

    // ========== Builder Methods ==========

    public CarTripRequestDtoTestBuilder withTravelerId(UUID travelerId) {
        this.travelerId = travelerId;
        return this;
    }

    public CarTripRequestDtoTestBuilder withOrigin(LocationRequestDto origin) {
        this.origin = origin;
        return this;
    }

    public CarTripRequestDtoTestBuilder withOrigin(String name, String city, String country, double latitude, double longitude) {
        this.origin = createLocationRequestDto(name, city, country, latitude, longitude);
        return this;
    }

    public CarTripRequestDtoTestBuilder withDestination(LocationRequestDto destination) {
        this.destination = destination;
        return this;
    }

    public CarTripRequestDtoTestBuilder withDestination(String name, String city, String country, double latitude, double longitude) {
        this.destination = createLocationRequestDto(name, city, country, latitude, longitude);
        return this;
    }

    public CarTripRequestDtoTestBuilder withDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public CarTripRequestDtoTestBuilder withArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public CarTripRequestDtoTestBuilder withAvailableWeight(BigDecimal weight) {
        this.availableWeight = weight;
        return this;
    }

    public CarTripRequestDtoTestBuilder withAvailableWeight(double weight) {
        this.availableWeight = BigDecimal.valueOf(weight);
        return this;
    }

    public CarTripRequestDtoTestBuilder withPricePerKg(BigDecimal price) {
        this.pricePerKg = price;
        return this;
    }

    public CarTripRequestDtoTestBuilder withPricePerKg(double price) {
        this.pricePerKg = BigDecimal.valueOf(price);
        return this;
    }

    public CarTripRequestDtoTestBuilder withPriceAsked(BigDecimal price) {
        this.priceAsked = price;
        return this;
    }

    public CarTripRequestDtoTestBuilder withPriceAsked(double price) {
        this.priceAsked = BigDecimal.valueOf(price);
        return this;
    }

    public CarTripRequestDtoTestBuilder withTransportType(TransportType type) {
        this.transportType = type;
        return this;
    }

    public CarTripRequestDtoTestBuilder withNotice(String notice) {
        this.notice = notice;
        return this;
    }

    public CarTripRequestDtoTestBuilder withCarDetails(CarTransportRequestDto carDetails) {
        this.carDetails = carDetails;
        return this;
    }

    public CarTripRequestDtoTestBuilder withCarDetails(
            String vehicleMake,
            String vehicleModel,
            Integer vehicleYear,
            String licensePlate,
            String vehicleColor
    ) {
        this.carDetails = new CarTransportRequestDto(
                vehicleMake,
                vehicleModel,
                vehicleYear,
                licensePlate,
                vehicleColor
        );
        return this;
    }

    public CarTripRequestDtoTestBuilder departingTomorrow() {
        this.departureDate = LocalDateTime.now().plusDays(1);
        this.arrivalDate = this.departureDate.plusHours(8);
        return this;
    }

    public CarTripRequestDtoTestBuilder departingNextWeek() {
        this.departureDate = LocalDateTime.now().plusDays(7);
        this.arrivalDate = this.departureDate.plusHours(8);
        return this;
    }

    // ========== Build Method ==========

    public CarTripRequestDto build() {
        return CarTripRequestDto.builder()
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
                .carDetails(carDetails)
                .build();
    }

    // ========== Helper Methods ==========

    private static LocationRequestDto createLocationRequestDto(String name, String city, String country, double latitude, double longitude) {
        return new LocationRequestDto(
                name,
                city,
                country,
                BigDecimal.valueOf(latitude),
                BigDecimal.valueOf(longitude)
        );
    }

    private static CarTransportRequestDto createDefaultCarTransportRequestDto() {
        return new CarTransportRequestDto(
                "Toyota",               // vehicleMake
                "Camry",                // vehicleModel
                2020,                   // vehicleYear
                "ABC123",               // licensePlate
                "Blue"                  // vehicleColor
        );
    }
}

