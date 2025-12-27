// ===================================
// Specific RequestDTO for Flight Trip
// ===================================

package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class FlightTripRequestDTO extends TripRequestDTO {

    @NotNull
    @Valid
    @JsonProperty("flight_details")
    FlightTransportRequestDTO flightDetails;

}
