package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarTransportResponseDTO extends TransportResponseDTO{

    UUID id;

    @JsonProperty("vehicle_make")
    String vehicleMake;

    @JsonProperty("vehicle_model")
    String vehicleModel;

    @JsonProperty("vehicle_year")
    Integer vehicleYear;

    @JsonProperty("license_plate")
    String licensePlate;

    @JsonProperty("vehicle_color")
    String vehicleColor;

}
