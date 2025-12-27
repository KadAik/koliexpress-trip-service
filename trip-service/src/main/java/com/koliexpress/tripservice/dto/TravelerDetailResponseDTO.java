package com.koliexpress.tripservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO for {@link com.koliexpress.tripservice.model.Traveler}
 */
@Value
public class TravelerDetailResponseDTO implements Serializable {
    UUID id;

    @JsonProperty("trip_ids")
    List<UUID> tripIds;
}