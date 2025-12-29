// ===================================
// Specific RequestDTO for Flight Trip
// ===================================

package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
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

    @NotNull
    @Valid
    @JsonProperty("flight_details")
    FlightTransportRequestDTO flightDetails;

}
