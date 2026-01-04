package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightTransportResponseDto extends TransportResponseDto {

    @JsonProperty("flight_number")
    String flightNumber;

    @JsonProperty("airline_code")
    String airlineCode;

    @JsonProperty("departure_airport")
    String departureAirportCode;

    @JsonProperty("arrival_airport")
    String arrivalAirportCode;

    @JsonProperty("aircraft_type")
    String aircraftType;

    @JsonProperty("additional_info")
    String additionalInfo;
}

