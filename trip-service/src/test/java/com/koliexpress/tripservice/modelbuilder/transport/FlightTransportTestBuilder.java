package com.koliexpress.tripservice.modelbuilder.transport;

import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import com.koliexpress.tripservice.model.transport.FlightTransport;

import java.time.LocalDateTime;
import java.util.UUID;

public class FlightTransportTestBuilder {

    private UUID id = UUID.randomUUID();
    private String flightNumber = "ET500";
    private String airlineCode = "ET";
    private String providerName = "Ethiopian Airlines";
    private String departureAirportCode = "COO";
    private String arrivalAirportCode = "LOS";
    private String bookingReference = "ABC123";
    private String aircraftType = "Boeing 737";
    private TransportVerificationStatus verificationStatus = TransportVerificationStatus.PENDING;

    private FlightTransportTestBuilder() {}

    public static FlightTransportTestBuilder aFlight() {
        return new FlightTransportTestBuilder();
    }

    public static FlightTransportTestBuilder anEthiopianFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("ET500")
                .withAirlineCode("ET")
                .withProviderName("Ethiopian Airlines");
    }

    public FlightTransportTestBuilder aDeltaAirlinesFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("DL100")
                .withAirlineCode("DL")
                .withProviderName("Delta Airlines");
    }

    public FlightTransportTestBuilder withFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightTransportTestBuilder withAircraftType(String airCraftType) {
        this.aircraftType = airCraftType;
        return this;
    }

    public FlightTransportTestBuilder withAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    public FlightTransportTestBuilder withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public FlightTransportTestBuilder withBookingReference(String reference) {
        this.bookingReference = reference;
        return this;
    }

    public FlightTransportTestBuilder withArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
        return this;
    }

    public FlightTransportTestBuilder withDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    public FlightTransportTestBuilder withVerificationStatus(TransportVerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
        return this;
    }

    public FlightTransportTestBuilder verified() {
        this.verificationStatus = TransportVerificationStatus.VERIFIED;
        return this;
    }

    public FlightTransportTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    public FlightTransport build() {
        FlightTransport flight = new FlightTransport();
        flight.setId(id);
        flight.setFlightNumber(flightNumber);
        flight.setAircraftType(aircraftType);
        flight.setAirlineCode(airlineCode);
        flight.setProviderName(providerName);
        flight.setDepartureAirportCode(departureAirportCode);
        flight.setArrivalAirportCode(arrivalAirportCode);
        flight.setBookingReference(bookingReference);
        flight.setVerificationStatus(verificationStatus);
        flight.setCreatedAt(LocalDateTime.now());
        flight.setUpdatedAt(LocalDateTime.now());
        return flight;
    }
}
