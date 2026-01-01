package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class FlightTripRequestDTO extends TripRequestDTO {

    @NotNull(
            message = "Flight details are required",
            groups = {ValidationGroups.Create.class}
    )
    @Valid
    @JsonProperty("flight_details")
    private FlightTransportRequestDTO flightDetails;

    public TransportType setTransportType() {
        return TransportType.PLANE;
    }



}
