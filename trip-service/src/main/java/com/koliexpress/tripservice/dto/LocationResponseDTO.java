package com.koliexpress.tripservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class LocationResponseDTO implements Serializable {
    String name;
    String city;
    String country;
    BigDecimal latitude;
    BigDecimal longitude;
}
