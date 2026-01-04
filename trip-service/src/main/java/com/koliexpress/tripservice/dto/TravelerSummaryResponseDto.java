package com.koliexpress.tripservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dto for {@link com.koliexpress.tripservice.model.Traveler}
 */
@Value
public class TravelerSummaryResponseDto implements Serializable {
    UUID id;

    @JsonProperty("name")
    String name;

    @JsonProperty("contact")
    String contact;

    @JsonProperty("active_trip_count")
    int activeTripCount;
}
