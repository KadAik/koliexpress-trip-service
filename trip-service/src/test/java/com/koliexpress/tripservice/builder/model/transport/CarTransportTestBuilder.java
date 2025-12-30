package com.koliexpress.tripservice.builder.model.transport;

import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import com.koliexpress.tripservice.model.transport.CarTransport;

import java.util.UUID;

public class CarTransportTestBuilder {

    private UUID id = UUID.randomUUID();
    private String vehicleMake = "Toyota";
    private String vehicleModel = "Camry";
    private Integer vehicleYear = 2022;
    private String licensePlate = "AB-123-CD";
    private String vehicleColor = "White";
    private String providerName = "Private";
    private TransportVerificationStatus verificationStatus = TransportVerificationStatus.PENDING;

    private CarTransportTestBuilder() {}

    public static CarTransportTestBuilder aCar() {
        return new CarTransportTestBuilder();
    }

    public CarTransportTestBuilder withVehicleMake(String make) {
        this.vehicleMake = make;
        return this;
    }

    public CarTransportTestBuilder withVehicleModel(String model) {
        this.vehicleModel = model;
        return this;
    }

    public CarTransportTestBuilder withVehicleYear(Integer year) {
        this.vehicleYear = year;
        return this;
    }

    public CarTransportTestBuilder withLicensePlate(String plate) {
        this.licensePlate = plate;
        return this;
    }

    public CarTransportTestBuilder withVehicleColor(String color) {
        this.vehicleColor = color;
        return this;
    }

    public CarTransportTestBuilder withProviderName(String provider) {
        this.providerName = provider;
        return this;
    }

    public CarTransportTestBuilder withVerificationStatus(TransportVerificationStatus status) {
        this.verificationStatus = status;
        return this;
    }

    public CarTransportTestBuilder withoutID() {
        this.id = null;
        return this;
    }

    public CarTransport build() {
        CarTransport car = new CarTransport();
        car.setId(id);
        car.setVehicleMake(vehicleMake);
        car.setVehicleModel(vehicleModel);
        car.setVehicleYear(vehicleYear);
        car.setLicensePlate(licensePlate);
        car.setVehicleColor(vehicleColor);
        car.setProviderName(providerName);
        car.setVerificationStatus(verificationStatus);
        return car;
    }
}
