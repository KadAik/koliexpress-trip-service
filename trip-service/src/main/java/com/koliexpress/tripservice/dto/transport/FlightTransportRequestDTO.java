package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
public class FlightTransportRequestDTO extends TransportRequestDTO {

    @JsonProperty("aircraft_type")
    @NotBlank
    String aircraftType;

    @JsonProperty("flight_number")
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}[0-9]{1,4}$", message = "Invalid flight number format")
    String flightNumber;

    @JsonProperty("airline_code")
    @NotBlank
    @Size(min = 2, max = 2)
    String airlineCode;

    @JsonProperty("booking_reference")
    String bookingReference;

    @JsonProperty("departure_airport_code")
    @NotBlank
    @Size(min = 3, max = 3)
    String departureAirportCode;

    @JsonProperty("arrival_airport_code")
    @NotBlank
    @Size(min = 3, max = 3)
    String arrivalAirportCode;
}
