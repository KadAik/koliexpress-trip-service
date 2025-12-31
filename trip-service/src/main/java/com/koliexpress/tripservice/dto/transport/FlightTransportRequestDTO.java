package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlightTransportRequestDTO extends TransportRequestDTO {

    @NotBlank(
            message = "Aircraft type is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Size(
            max = 50, message = "Aircraft type cannot exceed 50 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("aircraft_type")
    private String aircraftType;

    @NotBlank(
            message = "Flight number is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Pattern(
            regexp = "^[A-Z]{2}[0-9]{1,4}$",
            message = "Flight number must be 2 letters followed by 1-4 digits",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("flight_number")
    private String flightNumber;

    @NotBlank(
            message = "Airline code is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Pattern(
            regexp = "^[A-Z]{2}$",
            message = "Airline code must be 2 uppercase letters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("airline_code")
    private String airlineCode;

    @Size(
            max = 20, message = "Booking reference cannot exceed 20 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("booking_reference")
    private String bookingReference;

    @NotBlank(
            message = "Departure airport code is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Departure airport code must be 3 uppercase letters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("departure_airport_code")
    private String departureAirportCode;

    @NotBlank(
            message = "Arrival airport code is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Arrival airport code must be 3 uppercase letters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("arrival_airport_code")
    private String arrivalAirportCode;
}