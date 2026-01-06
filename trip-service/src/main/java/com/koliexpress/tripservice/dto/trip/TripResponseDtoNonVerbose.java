package com.koliexpress.tripservice.dto.trip;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.dto.LocationResponseDto;
import com.koliexpress.tripservice.dto.transport.TransportResponseDtoNonVerbose;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TripStatus;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Dto for {@link com.koliexpress.tripservice.model.Trip}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TripResponseDtoNonVerbose implements Serializable {
    private UUID id;

    private String url;

    @JsonProperty(value = "traveler_id")
    private UUID travelerId;

    private LocationResponseDto origin;
    private LocationResponseDto destination;

    @JsonProperty(value = "departure_date")
    private LocalDateTime departureDate;

    @JsonProperty("arrival_date")
    private LocalDateTime arrivalDate;

    @JsonProperty("available_weight")
    private BigDecimal availableWeight;

    @JsonProperty("price_per_kg")
    private BigDecimal pricePerKg;

    @JsonProperty("price_asked")
    private BigDecimal priceAsked;

    @JsonProperty("transport_type")
    private TransportType transportType;

    @JsonProperty("transport")
    private TransportResponseDtoNonVerbose transport;

    TripStatus status;
    private String notice;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}