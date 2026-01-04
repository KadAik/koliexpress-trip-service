package com.koliexpress.tripservice.builder.dto.trip;

import com.koliexpress.tripservice.dto.LocationRequestDTO;
import com.koliexpress.tripservice.dto.trip.CarTripRequestDTO;
import com.koliexpress.tripservice.dto.transport.CarTransportRequestDTO;
import com.koliexpress.tripservice.enums.TransportType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Builder for creating CarTripRequestDTO instances in tests
 * Uses the Builder pattern.
 */
public class CarTripRequestDtoTestBuilder {

    // Default values
    private UUID travelerId = UUID.randomUUID();
    private LocationRequestDTO origin = createLocationRequestDTO("Pickup Point", "Cotonou", "Benin", 6.357, 2.384);
    private LocationRequestDTO destination = createLocationRequestDTO("Drop-off Point", "Lagos", "Nigeria", 6.577, 3.321);
    private LocalDateTime departureDate = LocalDateTime.now().plusDays(7);
    private LocalDateTime arrivalDate = LocalDateTime.now().plusDays(7).plusHours(8);
    private BigDecimal availableWeight = BigDecimal.valueOf(30);
    private BigDecimal pricePerKg = BigDecimal.valueOf(3);
    private BigDecimal priceAsked = BigDecimal.valueOf(90);
    private TransportType transportType = TransportType.CAR;
    private String notice = "Handle packages carefully.";
    private CarTransportRequestDTO carDetails = createDefaultCarTransportRequestDTO();

    private CarTripRequestDtoTestBuilder() {}

     /**
     * Create a default CarTripRequestDTO
     */
    public static CarTripRequestDtoTestBuilder aCarTripRequestDto() {
        return new CarTripRequestDtoTestBuilder();
    }

    /**
     * Create a CarTripRequestDTO from Cotonou to Lagos
     */
    public static CarTripRequestDtoTestBuilder aCotonouToLagosCarTripRequest() {
        return new CarTripRequestDtoTestBuilder()
                .withOrigin(createLocationRequestDTO("Pickup Point", "Cotonou", "Benin", 6.357, 2.384))
                .withDestination(createLocationRequestDTO("Drop-off Point", "Lagos", "Nigeria", 6.577, 3.321));
    }

    /**
     * Create a CarTripRequestDTO departing tomorrow
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

    public CarTripRequestDtoTestBuilder withOrigin(LocationRequestDTO origin) {
        this.origin = origin;
        return this;
    }

    public CarTripRequestDtoTestBuilder withOrigin(String name, String city, String country, double latitude, double longitude) {
        this.origin = createLocationRequestDTO(name, city, country, latitude, longitude);
        return this;
    }

    public CarTripRequestDtoTestBuilder withDestination(LocationRequestDTO destination) {
        this.destination = destination;
        return this;
    }

    public CarTripRequestDtoTestBuilder withDestination(String name, String city, String country, double latitude, double longitude) {
        this.destination = createLocationRequestDTO(name, city, country, latitude, longitude);
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

    public CarTripRequestDtoTestBuilder withCarDetails(CarTransportRequestDTO carDetails) {
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
        this.carDetails = new CarTransportRequestDTO(
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

    public CarTripRequestDTO build() {
        return CarTripRequestDTO.builder()
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

    private static LocationRequestDTO createLocationRequestDTO(String name, String city, String country, double latitude, double longitude) {
        return new LocationRequestDTO(
                name,
                city,
                country,
                BigDecimal.valueOf(latitude),
                BigDecimal.valueOf(longitude)
        );
    }

    private static CarTransportRequestDTO createDefaultCarTransportRequestDTO() {
        return new CarTransportRequestDTO(
                "Toyota",               // vehicleMake
                "Camry",                // vehicleModel
                2020,                   // vehicleYear
                "ABC123",               // licensePlate
                "Blue"                  // vehicleColor
        );
    }
}

