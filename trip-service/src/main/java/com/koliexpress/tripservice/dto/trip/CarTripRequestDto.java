// ===================================
// Specific RequestDto for a Car Trip
// ===================================

package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.transport.CarTransportRequestDto;
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
public class CarTripRequestDto extends TripRequestDto {



    @NotNull(
            message = "Car details are required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Valid
    @JsonProperty("car_details")
    CarTransportRequestDto carDetails;

}

