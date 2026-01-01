package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusTransportRequestDTO extends TransportRequestDTO {

    @NotBlank(
            message = "Bus company is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 100, message = "Bus company cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("bus_company")
    private String busCompany;

    @Size(
            max = 20, message = "Bus number cannot exceed 20 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("bus_number")
    private String busNumber;

    @NotBlank(
            message = "Departure station is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 100, message = "Departure station cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("departure_station")
    private String departureStation;

    @NotBlank(
            message = "Arrival station is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 100, message = "Arrival station cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("arrival_station")
    private String arrivalStation;
}