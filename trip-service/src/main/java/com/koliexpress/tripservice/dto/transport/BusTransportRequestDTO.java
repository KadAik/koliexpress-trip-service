package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
public class BusTransportRequestDTO extends TransportRequestDTO {

    @NotBlank
    @NotNull
    @JsonProperty("bus_company")
    String busCompany;


    @JsonProperty("bus_number")
    String busNumber;

    @NotBlank
    @NotNull
    @JsonProperty("departure_station")
    String departureStation;

    @NotBlank
    @NotNull
    @JsonProperty("arrival_station")
    String arrivalStation;
}
