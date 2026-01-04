package com.koliexpress.tripservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Dto for {@link com.koliexpress.tripservice.model.Traveler}
 */
@Value
public class TravelerDetailResponseDto implements Serializable {
    UUID id;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("email")
    String email;

    @JsonProperty("phone_number")
    String phoneNumber;

    @JsonProperty("trip_ids")
    List<UUID> tripIds;
}