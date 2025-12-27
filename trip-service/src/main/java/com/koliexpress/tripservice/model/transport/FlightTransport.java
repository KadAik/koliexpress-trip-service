// =====================
// Transport by PLANE
// =====================
package com.koliexpress.tripservice.model.transport;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("FLIGHT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlightTransport extends Transport {

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "airline_code")
    private String airlineCode;

    @Column(name = "booking_reference")
    private String bookingReference;

    @Column(name = "departure_airport_code", length = 10)
    private String departureAirportCode;

    @Column(name = "arrival_airport_code", length = 10)
    private String arrivalAirportCode;

    @Column(name = "aircraft_type")
    private String aircraftType;

    @Override
    public boolean validate() {
        return flightNumber != null && !flightNumber.isEmpty()
                && departureAirportCode != null && !departureAirportCode.isEmpty()
                && arrivalAirportCode != null && !arrivalAirportCode.isEmpty();
    }

    @Override
    public String getSummary() {
        return String.format("Flight %s from %s to %s",
                flightNumber, departureAirportCode, arrivalAirportCode);
    }
}

