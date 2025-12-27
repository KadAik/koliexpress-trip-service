package com.koliexpress.tripservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for {@link com.koliexpress.tripservice.model.Traveler}
 */
@Value
public class TravelerSummaryResponseDTO implements Serializable {
    UUID id;

    @JsonProperty("active_trip_count")
    int activeTripCount;
}
