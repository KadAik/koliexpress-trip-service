package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.enums.TransportType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
public class CarTransportResponseDto extends TransportResponseDto {

    UUID id;

    @JsonProperty("transport_type")
    TransportType transportType = TransportType.CAR;

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
