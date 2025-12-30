package com.koliexpress.tripservice.builder.dto.transport;

import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;

public class FlightTransportRequestDtoTestBuilder {
    private String providerName = "American Airlines";
    private String referenceNumber = "REF123";
    private String additionalInfo = "Flight includes meal service";
    private String aircraftType = "Boeing 737";
    private String flightNumber = "AA123";
    private String airlineCode = "AA";
    private String bookingReference = "ABC123";
    private String departureAirportCode = "JFK";
    private String arrivalAirportCode = "LAX";

    private FlightTransportRequestDtoTestBuilder() {
    }

    public static FlightTransportRequestDtoTestBuilder aFlightTransportRequestDto() {
        return new FlightTransportRequestDtoTestBuilder();
    }

    public FlightTransportRequestDtoTestBuilder withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    public FlightTransportRequestDtoTestBuilder withArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
        return this;
    }

    public FlightTransportRequestDTO build() {
        FlightTransportRequestDTO dto = new FlightTransportRequestDTO();
        dto.setProviderName(providerName);
        dto.setReferenceNumber(referenceNumber);
        dto.setAdditionalInfo(additionalInfo);
        dto.setAircraftType(aircraftType);
        dto.setFlightNumber(flightNumber);
        dto.setAirlineCode(airlineCode);
        dto.setBookingReference(bookingReference);
        dto.setDepartureAirportCode(departureAirportCode);
        dto.setArrivalAirportCode(arrivalAirportCode);
        return dto;
    }

    // Convenience methods for common test scenarios
    public static FlightTransportRequestDTO validFlightTransport() {
        return aFlightTransportRequestDto().build();
    }

    public static FlightTransportRequestDTO withEmptyProviderName() {
        return aFlightTransportRequestDto()
                .withProviderName("")
                .build();
    }

    public static FlightTransportRequestDTO withInvalidFlightNumber() {
        return aFlightTransportRequestDto()
                .withFlightNumber("123AA")
                .build();
    }

    public static FlightTransportRequestDTO domesticFlight() {
        return aFlightTransportRequestDto()
                .withProviderName("Delta Airlines")
                .withFlightNumber("DL456")
                .withAirlineCode("DL")
                .withDepartureAirportCode("ATL")
                .withArrivalAirportCode("DFW")
                .withAircraftType("Airbus A321")
                .withReferenceNumber("DLREF456")
                .withAdditionalInfo("Domestic flight with Wi-Fi")
                .build();
    }

    public static FlightTransportRequestDTO internationalFlight() {
        return aFlightTransportRequestDto()
                .withProviderName("British Airways")
                .withFlightNumber("BA789")
                .withAirlineCode("BA")
                .withDepartureAirportCode("LHR")
                .withArrivalAirportCode("JFK")
                .withAircraftType("Boeing 777")
                .withBookingReference("XYZ789")
                .withReferenceNumber("BAREF789")
                .withAdditionalInfo("International flight with premium economy")
                .build();
    }

    public static FlightTransportRequestDTO longHaulFlight() {
        return aFlightTransportRequestDto()
                .withProviderName("Singapore Airlines")
                .withFlightNumber("SQ123")
                .withAirlineCode("SQ")
                .withDepartureAirportCode("SIN")
                .withArrivalAirportCode("SFO")
                .withAircraftType("Airbus A350")
                .withBookingReference("SINGAPORE123")
                .withReferenceNumber("SQREF123")
                .withAdditionalInfo("Long-haul flight with premium suites")
                .build();
    }
}