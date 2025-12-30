package com.koliexpress.tripservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class LocationRequestDTO implements Serializable {

    @NotNull
    String name;

    @NotBlank
    String city;

    @NotBlank
    String country;

    @NotNull
    @Min(-90) @Max(90)
    BigDecimal latitude;

    @NotNull
    @Min(-180) @Max(180)
    BigDecimal longitude;
}
