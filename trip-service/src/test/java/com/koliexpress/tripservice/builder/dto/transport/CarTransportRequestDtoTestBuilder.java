package com.koliexpress.tripservice.builder.dto.transport;

import com.koliexpress.tripservice.dto.transport.CarTransportRequestDTO;

public class CarTransportRequestDtoTestBuilder {
    private String providerName = "Enterprise Rent-A-Car";
    private String referenceNumber = "CARREF001";
    private String additionalInfo = "Mid-size sedan with automatic transmission";
    private String vehicleMake = "Toyota";
    private String vehicleModel = "Camry";
    private Integer vehicleYear = 2022;
    private String licensePlate = "ABC123";
    private String vehicleColor = "Silver";

    private CarTransportRequestDtoTestBuilder() {
    }

    public static CarTransportRequestDtoTestBuilder aCarTransportRequestDto() {
        return new CarTransportRequestDtoTestBuilder();
    }

    public CarTransportRequestDtoTestBuilder withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public CarTransportRequestDtoTestBuilder withVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
        return this;
    }

    public CarTransportRequestDTO build() {
        CarTransportRequestDTO dto = new CarTransportRequestDTO();
        dto.setProviderName(providerName);
        dto.setReferenceNumber(referenceNumber);
        dto.setAdditionalInfo(additionalInfo);
        dto.setVehicleMake(vehicleMake);
        dto.setVehicleModel(vehicleModel);
        dto.setVehicleYear(vehicleYear);
        dto.setLicensePlate(licensePlate);
        dto.setVehicleColor(vehicleColor);
        return dto;
    }

    // Convenience methods for common test scenarios
    public static CarTransportRequestDTO validCarTransport() {
        return aCarTransportRequestDto().build();
    }

    public static CarTransportRequestDTO withEmptyVehicleMake() {
        return aCarTransportRequestDto()
                .withVehicleMake("")
                .build();
    }

    public static CarTransportRequestDTO withEmptyLicensePlate() {
        return aCarTransportRequestDto()
                .withLicensePlate("")
                .build();
    }

    public static CarTransportRequestDTO luxuryCar() {
        return aCarTransportRequestDto()
                .withProviderName("Hertz Gold")
                .withVehicleMake("Mercedes-Benz")
                .withVehicleModel("S-Class")
                .withVehicleYear(2023)
                .withLicensePlate("MERC123")
                .withVehicleColor("Black")
                .withReferenceNumber("HERTZGLD001")
                .withAdditionalInfo("Luxury sedan with premium package")
                .build();
    }

    public static CarTransportRequestDTO electricVehicle() {
        return aCarTransportRequestDto()
                .withProviderName("Tesla Rental")
                .withVehicleMake("Tesla")
                .withVehicleModel("Model 3")
                .withVehicleYear(2023)
                .withLicensePlate("EV789")
                .withVehicleColor("Red")
                .withReferenceNumber("TESLAREF001")
                .withAdditionalInfo("Electric vehicle with autopilot")
                .build();
    }

    public static CarTransportRequestDTO suvVehicle() {
        return aCarTransportRequestDto()
                .withProviderName("Avis Premium")
                .withVehicleMake("Ford")
                .withVehicleModel("Explorer")
                .withVehicleYear(2021)
                .withLicensePlate("SUV456")
                .withVehicleColor("Blue")
                .withReferenceNumber("AVISPRM001")
                .withAdditionalInfo("SUV with third-row seating")
                .build();
    }

    public static CarTransportRequestDTO vintageCar() {
        return aCarTransportRequestDto()
                .withProviderName("Classic Car Rentals")
                .withVehicleMake("Chevrolet")
                .withVehicleModel("Impala")
                .withVehicleYear(1967)
                .withLicensePlate("OLD67")
                .withVehicleColor("Cherry Red")
                .withReferenceNumber("CLASSIC001")
                .withAdditionalInfo("Vintage car with manual transmission")
                .build();
    }
}