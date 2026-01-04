package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.LocationResponseDTO;
import com.koliexpress.tripservice.dto.transport.TransportResponseDTO;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TripStatus;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.koliexpress.tripservice.model.Trip}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TripResponseDTO implements Serializable {
    UUID id;

    @JsonProperty(value = "traveler_id")
    UUID travelerId;

    LocationResponseDTO origin;
    LocationResponseDTO destination;

    @JsonProperty(value = "departure_date")
    LocalDateTime departureDate;

    @JsonProperty("arrival_date")
    LocalDateTime arrivalDate;

    @JsonProperty("available_weight")
    BigDecimal availableWeight;

    @JsonProperty("price_per_kg")
    BigDecimal pricePerKg;

    @JsonProperty("price_asked")
    BigDecimal priceAsked;

    @JsonProperty("transport_type")
    TransportType transportType;

    @JsonProperty("transport_details")
    TransportResponseDTO transportDetails;

    TripStatus status;
    String notice;

    @JsonProperty("created_at")
    LocalDateTime createdAt;

    @JsonProperty("updated_at")
    LocalDateTime updatedAt;

}