package com.koliexpress.tripservice.dto;

import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class LocationRequestDto implements Serializable {

    @NotBlank(
            message = "Location name is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Size(
            max = 200, message = "Location name cannot exceed 200 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    String name;

    @NotBlank(
            message = "City is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Size(
            max = 100, message = "City cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    String city;

    @NotBlank(
            message = "Country is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Size(
            min = 2, max = 100, message = "Country must be between 2 and 100 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    String country;

    @NotNull(
            message = "Latitude is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @DecimalMin(
            value = "-90.0", message = "Latitude must be at least -90",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @DecimalMax(
            value = "90.0", message = "Latitude cannot exceed 90",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    BigDecimal latitude;

    @NotNull(
            message = "Longitude is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @DecimalMin(
            value = "-180.0", message = "Longitude must be at least -180",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @DecimalMax(
            value = "180.0", message = "Longitude cannot exceed 180",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    BigDecimal longitude;
}