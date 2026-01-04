package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.LocationRequestDTO;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base DTO for TripRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class TripRequestDTO implements Serializable {

    @JsonProperty("traveler_id")
    @NotNull(message = "Traveler ID is required", groups = ValidationGroups.Create.class)
    private UUID travelerId;

    @NotNull(message = "Origin location is required", groups = ValidationGroups.Create.class)
    @Valid
    private LocationRequestDTO origin;

    @NotNull(message = "Destination location is required", groups = ValidationGroups.Create.class)
    @Valid
    private LocationRequestDTO destination;

    @JsonProperty("departure_date")
    @NotNull(message = "Departure date is required", groups = ValidationGroups.Create.class)
    @FutureOrPresent(
            message = "Departure date must be in the present or future",
            groups = ValidationGroups.Create.class
    )
    private LocalDateTime departureDate;

    @JsonProperty("arrival_date")
    @NotNull(message = "Arrival date is required", groups = ValidationGroups.Create.class)
    @Future(
            message = "Arrival date must be in the future",
            groups = ValidationGroups.Create.class
    )
    private LocalDateTime arrivalDate;

    @JsonProperty("available_weight")
    @NotNull(message = "Available weight is required", groups = ValidationGroups.Create.class)
    @Positive(
            message = "Available weight must be greater than 0",
            groups = ValidationGroups.Create.class
    )
    @Digits(integer = 10, fraction = 2,
            message = "Available weight must have at most 2 decimal places",
            groups = ValidationGroups.Create.class
    )
    private BigDecimal availableWeight;

    @JsonProperty("price_per_kg")
    @NotNull(message = "Price per kilogram is required", groups = ValidationGroups.Create.class)
    @Positive(message = "Price per kilogram must be greater than 0",
            groups = ValidationGroups.Create.class
    )
    @Digits(integer = 10, fraction = 2,
            message = "Price per kilogram must have at most 2 decimal places",
            groups = ValidationGroups.Create.class
    )
    private BigDecimal pricePerKg;

    @JsonProperty("price_asked")
    @NotNull(message = "Total price is required", groups = ValidationGroups.Create.class)
    @Positive(message = "Total price must be greater than 0",
            groups = ValidationGroups.Create.class)
    @Digits(integer = 10, fraction = 2,
            message = "Total price must have at most 2 decimal places",
            groups = ValidationGroups.Create.class
    )
    private BigDecimal priceAsked;

    @Size(max = 500, message = "Notice cannot exceed 500 characters",
            groups = ValidationGroups.Create.class
    )
    private String notice;

    // ========== Custom Validations ==========

    @AssertTrue(message = "Arrival date must be after departure date",
            groups = ValidationGroups.Create.class
    )
    private boolean isArrivalAfterDeparture() {
        if (departureDate == null || arrivalDate == null) {
            return true; // Let @NotBlank handle null cases
        }
        return arrivalDate.isAfter(departureDate);
    }

    @AssertTrue(message = "Available weight must be reasonable (max 1000 kg)",
            groups = ValidationGroups.Create.class
    )
    private boolean isValidWeight() {
        if (availableWeight == null) {
            return true;
        }
        return availableWeight.compareTo(BigDecimal.valueOf(1000)) <= 0;
    }

    @AssertTrue(message = "Price per kg cannot exceed total price",
            groups = ValidationGroups.Create.class
    )
    private boolean isValidPriceRatio() {
        if (pricePerKg == null || priceAsked == null || availableWeight == null
                || availableWeight.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return pricePerKg.multiply(availableWeight).compareTo(priceAsked) <= 0;
    }
}