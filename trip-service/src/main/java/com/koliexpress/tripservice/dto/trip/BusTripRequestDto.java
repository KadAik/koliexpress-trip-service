// ==================================
// Specific RequestDto for a Bus Trip
// ==================================

package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.BusTransportRequestDto;
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
public class BusTripRequestDto extends TripRequestDto {

    @NotNull(
            message = "Bus details are required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Valid
    @JsonProperty("bus_details")
    BusTransportRequestDto busDetails;

}
