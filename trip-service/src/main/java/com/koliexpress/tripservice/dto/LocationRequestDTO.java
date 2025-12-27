package com.koliexpress.tripservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class LocationRequestDTO implements Serializable {
    String name;

    @NotBlank
    String city;

    @NotBlank
    String country;

    @Min(-90) @Max(90)
    BigDecimal latitude;

    @Min(-180) @Max(180)
    BigDecimal longitude;
}
