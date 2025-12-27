package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
public class CarTransportRequestDTO extends TransportRequestDTO {

    @NotBlank
    @NotNull
    @JsonProperty("vehicle_make")
    String vehicleMake;

    @NotBlank
    @NotNull
    @JsonProperty("vehicle_model")
    String vehicleModel;

    @JsonProperty("vehicle_year")
    Integer vehicleYear;

    @NotBlank
    @NotNull
    @JsonProperty("license_plate")
    String licensePlate;

    @JsonProperty("vehicle_color")
    String vehicleColor;

}
