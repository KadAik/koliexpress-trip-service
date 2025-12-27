// ==================================
// Specific RequestDTO for a Bus Trip
// ==================================

package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.BusTransportRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
public class BusTripRequestDTO extends TripRequestDTO {

    @NotNull
    @Valid
    @JsonProperty("bus_details")
    BusTransportRequestDTO busDetails;

}
