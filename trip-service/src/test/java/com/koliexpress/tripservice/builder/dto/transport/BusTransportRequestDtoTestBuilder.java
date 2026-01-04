package com.koliexpress.tripservice.builder.dto.transport;

import com.koliexpress.tripservice.dto.transport.BusTransportRequestDto;

public class BusTransportRequestDtoTestBuilder {
    private String providerName = "Greyhound Lines";
    private String referenceNumber = "BUSREF001";
    private String additionalInfo = "Standard coach service";
    private String busCompany = "Greyhound";
    private String busNumber = "GN123";
    private String departureStation = "New York Port Authority";
    private String arrivalStation = "Boston South Station";

    private BusTransportRequestDtoTestBuilder() {
    }

    public static BusTransportRequestDtoTestBuilder aBusTransportRequestDto() {
        return new BusTransportRequestDtoTestBuilder();
    }

    public BusTransportRequestDtoTestBuilder withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public BusTransportRequestDtoTestBuilder withReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public BusTransportRequestDtoTestBuilder withAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public BusTransportRequestDtoTestBuilder withBusCompany(String busCompany) {
        this.busCompany = busCompany;
        return this;
    }

    public BusTransportRequestDtoTestBuilder withBusNumber(String busNumber) {
        this.busNumber = busNumber;
        return this;
    }

    public BusTransportRequestDtoTestBuilder withDepartureStation(String departureStation) {
        this.departureStation = departureStation;
        return this;
    }

    public BusTransportRequestDtoTestBuilder withArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
        return this;
    }

    public BusTransportRequestDto build() {
        BusTransportRequestDto dto = new BusTransportRequestDto();
        dto.setProviderName(providerName);
        dto.setReferenceNumber(referenceNumber);
        dto.setAdditionalInfo(additionalInfo);
        dto.setBusCompany(busCompany);
        dto.setBusNumber(busNumber);
        dto.setDepartureStation(departureStation);
        dto.setArrivalStation(arrivalStation);
        return dto;
    }

    // Convenience methods for common test scenarios
    public static BusTransportRequestDto validBusTransport() {
        return aBusTransportRequestDto().build();
    }

    public static BusTransportRequestDto withEmptyBusCompany() {
        return aBusTransportRequestDto()
                .withBusCompany("")
                .build();
    }

    public static BusTransportRequestDto withEmptyDepartureStation() {
        return aBusTransportRequestDto()
                .withDepartureStation("")
                .build();
    }

    public static BusTransportRequestDto megabusService() {
        return aBusTransportRequestDto()
                .withProviderName("Megabus")
                .withBusCompany("Megabus")
                .withBusNumber("MEGA101")
                .withDepartureStation("Philadelphia 30th Street Station")
                .withArrivalStation("Washington Union Station")
                .withReferenceNumber("MEGAREF101")
                .withAdditionalInfo("Double decker bus with Wi-Fi")
                .build();
    }

    public static BusTransportRequestDto flixbusService() {
        return aBusTransportRequestDto()
                .withProviderName("FlixBus")
                .withBusCompany("FlixBus")
                .withBusNumber("FLX456")
                .withDepartureStation("Los Angeles Downtown Station")
                .withArrivalStation("San Francisco Caltrain Station")
                .withReferenceNumber("FLXREF456")
                .withAdditionalInfo("Eco-friendly bus with power outlets")
                .build();
    }

    public static BusTransportRequestDto europeanBus() {
        return aBusTransportRequestDto()
                .withProviderName("Eurolines")
                .withBusCompany("Eurolines")
                .withBusNumber("EUR789")
                .withDepartureStation("Paris Gallieni")
                .withArrivalStation("London Victoria Coach Station")
                .withReferenceNumber("EURREF789")
                .withAdditionalInfo("International coach service")
                .build();
    }
}