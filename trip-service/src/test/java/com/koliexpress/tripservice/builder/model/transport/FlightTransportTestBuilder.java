package com.koliexpress.tripservice.builder.model.transport;

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
    private String referenceNumber = "REF001";
    private String additionalInfo = "Flight includes meal service";
    private TransportVerificationStatus verificationStatus = TransportVerificationStatus.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
    private LocalDateTime updatedAt = LocalDateTime.now();

    private FlightTransportTestBuilder() {}

    public static FlightTransportTestBuilder aFlight() {
        return new FlightTransportTestBuilder();
    }

    public static FlightTransportTestBuilder anEthiopianFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("ET500")
                .withAirlineCode("ET")
                .withProviderName("Ethiopian Airlines")
                .withDepartureAirportCode("ADD")
                .withArrivalAirportCode("JNB");
    }

    public static FlightTransportTestBuilder aDeltaAirlinesFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("DL100")
                .withAirlineCode("DL")
                .withProviderName("Delta Airlines")
                .withDepartureAirportCode("ATL")
                .withArrivalAirportCode("LAX")
                .withAircraftType("Airbus A321");
    }

    public static FlightTransportTestBuilder aBritishAirwaysFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("BA200")
                .withAirlineCode("BA")
                .withProviderName("British Airways")
                .withDepartureAirportCode("LHR")
                .withArrivalAirportCode("JFK")
                .withAircraftType("Boeing 777");
    }

    public static FlightTransportTestBuilder aSingaporeAirlinesFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("SQ300")
                .withAirlineCode("SQ")
                .withProviderName("Singapore Airlines")
                .withDepartureAirportCode("SIN")
                .withArrivalAirportCode("SYD")
                .withAircraftType("Airbus A380");
    }

    public static FlightTransportTestBuilder aQatarAirwaysFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("QR400")
                .withAirlineCode("QR")
                .withProviderName("Qatar Airways")
                .withDepartureAirportCode("DOH")
                .withArrivalAirportCode("CDG")
                .withAircraftType("Boeing 787");
    }

    public static FlightTransportTestBuilder aBudgetAirlineFlight() {
        return new FlightTransportTestBuilder()
                .withFlightNumber("FR600")
                .withAirlineCode("FR")
                .withProviderName("Ryanair")
                .withDepartureAirportCode("STN")
                .withArrivalAirportCode("DUB")
                .withAircraftType("Boeing 737-800")
                .withAdditionalInfo("Budget airline - no checked baggage included");
    }

    // Builder methods
    public FlightTransportTestBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public FlightTransportTestBuilder withFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightTransportTestBuilder withAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
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

    public FlightTransportTestBuilder withBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
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

    public FlightTransportTestBuilder withReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public FlightTransportTestBuilder withAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public FlightTransportTestBuilder withVerificationStatus(TransportVerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
        return this;
    }

    public FlightTransportTestBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public FlightTransportTestBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    // Convenience status methods
    public FlightTransportTestBuilder verified() {
        this.verificationStatus = TransportVerificationStatus.VERIFIED;
        return this;
    }

    public FlightTransportTestBuilder pending() {
        this.verificationStatus = TransportVerificationStatus.PENDING;
        return this;
    }

    public FlightTransportTestBuilder rejected() {
        this.verificationStatus = TransportVerificationStatus.REJECTED;
        return this;
    }

    // Convenience creation methods
    public FlightTransportTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    public FlightTransportTestBuilder withoutBookingReference() {
        this.bookingReference = null;
        return this;
    }

    public FlightTransportTestBuilder withoutReferenceNumber() {
        this.referenceNumber = null;
        return this;
    }

    public FlightTransportTestBuilder withoutAdditionalInfo() {
        this.additionalInfo = null;
        return this;
    }

    public FlightTransportTestBuilder recentlyCreated() {
        this.createdAt = LocalDateTime.now().minusMinutes(30);
        this.updatedAt = LocalDateTime.now().minusMinutes(30);
        return this;
    }

    public FlightTransportTestBuilder createdDaysAgo(int days) {
        this.createdAt = LocalDateTime.now().minusDays(days);
        this.updatedAt = LocalDateTime.now().minusDays(days);
        return this;
    }

    // Convenience airport code methods
    public FlightTransportTestBuilder fromAirport(String airportCode) {
        this.departureAirportCode = airportCode;
        return this;
    }

    public FlightTransportTestBuilder toAirport(String airportCode) {
        this.arrivalAirportCode = airportCode;
        return this;
    }

    // Convenience for specific flight types
    public FlightTransportTestBuilder domesticFlight() {
        return this
                .withFlightNumber("AA123")
                .withAirlineCode("AA")
                .withProviderName("American Airlines")
                .withDepartureAirportCode("JFK")
                .withArrivalAirportCode("LAX")
                .withAircraftType("Boeing 737");
    }

    public FlightTransportTestBuilder internationalFlight() {
        return this
                .withFlightNumber("LH456")
                .withAirlineCode("LH")
                .withProviderName("Lufthansa")
                .withDepartureAirportCode("FRA")
                .withArrivalAirportCode("ORD")
                .withAircraftType("Boeing 747");
    }

    public FlightTransportTestBuilder cargoFlight() {
        return this
                .withFlightNumber("FX789")
                .withAirlineCode("FX")
                .withProviderName("FedEx")
                .withDepartureAirportCode("MEM")
                .withArrivalAirportCode("HKG")
                .withAircraftType("Boeing 777F")
                .withAdditionalInfo("Cargo flight - special handling required");
    }

    // Pre-built flight scenarios

    public static FlightTransport verifiedFlight() {
        return aFlight().verified().build();
    }

    public static FlightTransport pendingFlight() {
        return aFlight().pending().build();
    }


    public static FlightTransport deltaFlightInstance() {
        return aDeltaAirlinesFlight().build();
    }

    public static FlightTransport flightWithoutId() {
        return aFlight().withoutId().build();
    }

    public static FlightTransport flightWithoutBookingReference() {
        return aFlight().withoutBookingReference().build();
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
        flight.setReferenceNumber(referenceNumber);
        flight.setAdditionalInfo(additionalInfo);
        flight.setVerificationStatus(verificationStatus);
        flight.setCreatedAt(createdAt);
        flight.setUpdatedAt(updatedAt);
        return flight;
    }
}