package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.LocationRequestDTO;
import com.koliexpress.tripservice.enums.TransportType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base DTO for TripRequest
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class TripRequestDTO implements Serializable {

    @JsonProperty("traveler_id")
    @NotNull
    UUID travelerId;

    @NotNull
    @Valid
    LocationRequestDTO origin;

    @NotNull
    @Valid
    LocationRequestDTO destination;

    @JsonProperty("departure_date")
    @NotNull
    @FutureOrPresent
    LocalDateTime departureDate;

    @JsonProperty("arrival_date")
    @NotNull
    @Future
    LocalDateTime arrivalDate;

    @JsonProperty("available_weight")
    @NotNull
    @Positive
    BigDecimal availableWeight;

    @JsonProperty("price_per_kg")
    @NotNull
    @Positive
    BigDecimal pricePerKg;

    @JsonProperty("price_asked")
    @NotNull
    @Positive
    BigDecimal priceAsked;

    @NotNull
    @JsonProperty("transport_type")
    TransportType transportType;

    String notice;

}
