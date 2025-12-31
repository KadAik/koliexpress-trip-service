// ===================================
// Specific RequestDTO for a Car Trip
// ===================================

package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.CarTransportRequestDTO;
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
public class CarTripRequestDTO extends TripRequestDTO {



    @NotNull(
            message = "Car details are required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Valid
    @JsonProperty("car_details")
    CarTransportRequestDTO carDetails;

}

