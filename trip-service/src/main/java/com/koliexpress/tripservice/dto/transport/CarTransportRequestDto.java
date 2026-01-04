package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CarTransportRequestDto extends TransportRequestDto {

    @NotBlank(
            message = "Vehicle make is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 50, message = "Vehicle make cannot exceed 50 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("vehicle_make")
    private String vehicleMake;

    @NotBlank(
            message = "Vehicle model is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 50, message = "Vehicle model cannot exceed 50 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("vehicle_model")
    private String vehicleModel;

    @Min(
            value = 1900, message = "Vehicle year must be 1900 or later",
            groups = {ValidationGroups.Create.class, }
    )
    @Max(
            value = 2100, message = "Vehicle year cannot be in the distant future",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("vehicle_year")
    private Integer vehicleYear;

    @NotBlank(
            message = "License plate is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 20, message = "License plate cannot exceed 20 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @Pattern(
            regexp = "^[A-Z0-9\\-\\s]+$",
            message = "License plate can only contain letters, numbers, hyphens and spaces",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("license_plate")
    private String licensePlate;

    @Size(
            max = 30, message = "Vehicle color cannot exceed 30 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("vehicle_color")
    private String vehicleColor;
}