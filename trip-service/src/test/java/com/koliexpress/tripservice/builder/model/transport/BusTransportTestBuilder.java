package com.koliexpress.tripservice.builder.model.transport;

import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import com.koliexpress.tripservice.model.transport.BusTransport;

import java.time.LocalDateTime;
import java.util.UUID;

public class BusTransportTestBuilder {

    private UUID id = UUID.randomUUID();
    private String busCompany = "ABC Transport";
    private String busNumber = "BUS-789";
    private String referenceNumber = "REF-456";
    private String providerName = "ABC Transport";
    private String departureStation = "CMS Terminal Lagos";
    private String arrivalStation = "Jibowu Terminal Lagos";
    private String additionalInfo = "Standard coach service with air conditioning";
    private TransportVerificationStatus verificationStatus = TransportVerificationStatus.PENDING;
    private LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
    private LocalDateTime updatedAt = LocalDateTime.now();

    private BusTransportTestBuilder() {}

    public static BusTransportTestBuilder aBus() {
        return new BusTransportTestBuilder();
    }

    public static BusTransportTestBuilder aNigerianBus() {
        return new BusTransportTestBuilder()
                .withBusCompany("ABC Transport")
                .withBusNumber("ABC-001")
                .withProviderName("ABC Transport Nigeria")
                .withDepartureStation("Lagos Mainland Terminal")
                .withArrivalStation("Ibadan Central Station");
    }

    public static BusTransportTestBuilder aGreyhoundBus() {
        return new BusTransportTestBuilder()
                .withBusCompany("Greyhound")
                .withBusNumber("GN-100")
                .withProviderName("Greyhound Lines")
                .withDepartureStation("New York Port Authority")
                .withArrivalStation("Boston South Station")
                .withAdditionalInfo("Standard Greyhound coach with Wi-Fi");
    }

    public static BusTransportTestBuilder aMegabus() {
        return new BusTransportTestBuilder()
                .withBusCompany("Megabus")
                .withBusNumber("MEGA-200")
                .withProviderName("Megabus US")
                .withDepartureStation("Philadelphia 30th Street Station")
                .withArrivalStation("Washington Union Station")
                .withAdditionalInfo("Double-decker bus with power outlets");
    }

    public static BusTransportTestBuilder aFlixbus() {
        return new BusTransportTestBuilder()
                .withBusCompany("FlixBus")
                .withBusNumber("FLIX-300")
                .withProviderName("FlixBus Europe")
                .withDepartureStation("Berlin ZOB")
                .withArrivalStation("Amsterdam Sloterdijk")
                .withAdditionalInfo("Eco-friendly bus with onboard entertainment");
    }

    public static BusTransportTestBuilder aEurolinesBus() {
        return new BusTransportTestBuilder()
                .withBusCompany("Eurolines")
                .withBusNumber("EUR-400")
                .withProviderName("Eurolines International")
                .withDepartureStation("Paris Gallieni")
                .withArrivalStation("London Victoria Coach Station")
                .withAdditionalInfo("International coach with border crossing facilities");
    }

    public static BusTransportTestBuilder anAirportShuttle() {
        return new BusTransportTestBuilder()
                .withBusCompany("Airport Express")
                .withBusNumber("AE-500")
                .withProviderName("Airport Shuttle Service")
                .withDepartureStation("Downtown Terminal")
                .withArrivalStation("International Airport Terminal 1")
                .withAdditionalInfo("Direct airport shuttle with luggage compartments");
    }

    // Builder methods
    public BusTransportTestBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public BusTransportTestBuilder withBusCompany(String busCompany) {
        this.busCompany = busCompany;
        return this;
    }

    public BusTransportTestBuilder withBusNumber(String busNumber) {
        this.busNumber = busNumber;
        return this;
    }

    public BusTransportTestBuilder withProviderName(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public BusTransportTestBuilder withDepartureStation(String departureStation) {
        this.departureStation = departureStation;
        return this;
    }

    public BusTransportTestBuilder withArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
        return this;
    }

    public BusTransportTestBuilder withReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public BusTransportTestBuilder withAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
        return this;
    }

    public BusTransportTestBuilder withVerificationStatus(TransportVerificationStatus verificationStatus) {
        this.verificationStatus = verificationStatus;
        return this;
    }

    public BusTransportTestBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public BusTransportTestBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    // Convenience status methods
    public BusTransportTestBuilder verified() {
        this.verificationStatus = TransportVerificationStatus.VERIFIED;
        return this;
    }

    public BusTransportTestBuilder pending() {
        this.verificationStatus = TransportVerificationStatus.PENDING;
        return this;
    }

    public BusTransportTestBuilder rejected() {
        this.verificationStatus = TransportVerificationStatus.REJECTED;
        return this;
    }

    // Convenience creation methods
    public BusTransportTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    public BusTransportTestBuilder withoutBusNumber() {
        this.busNumber = null;
        return this;
    }

    public BusTransportTestBuilder withoutReferenceNumber() {
        this.referenceNumber = null;
        return this;
    }

    public BusTransportTestBuilder withoutAdditionalInfo() {
        this.additionalInfo = null;
        return this;
    }

    public BusTransportTestBuilder recentlyCreated() {
        this.createdAt = LocalDateTime.now().minusMinutes(30);
        this.updatedAt = LocalDateTime.now().minusMinutes(30);
        return this;
    }

    public BusTransportTestBuilder createdDaysAgo(int days) {
        this.createdAt = LocalDateTime.now().minusDays(days);
        this.updatedAt = LocalDateTime.now().minusDays(days);
        return this;
    }

    // Convenience station methods
    public BusTransportTestBuilder fromStation(String station) {
        this.departureStation = station;
        return this;
    }

    public BusTransportTestBuilder toStation(String station) {
        this.arrivalStation = station;
        return this;
    }

    // Convenience for specific bus types
    public BusTransportTestBuilder localBus() {
        return this
                .withBusCompany("City Transit")
                .withBusNumber("CT-001")
                .withProviderName("Metropolitan Transit Authority")
                .withDepartureStation("14th Street Union Square")
                .withArrivalStation("South Ferry Terminal")
                .withAdditionalInfo("Local city bus service")
                .withReferenceNumber("LOCAL-001");
    }

    public BusTransportTestBuilder longDistanceBus() {
        return this
                .withBusCompany("Trailways")
                .withBusNumber("TW-999")
                .withProviderName("Trailways Transportation")
                .withDepartureStation("Chicago Bus Terminal")
                .withArrivalStation("Denver Bus Station")
                .withAdditionalInfo("Long-distance coach with overnight facilities")
                .withReferenceNumber("LD-999");
    }

    // Pre-built bus scenarios

    public static BusTransport verifiedBus() {
        return aBus().verified().build();
    }

    public static BusTransport pendingBus() {
        return aBus().pending().build();
    }

    public static BusTransport busWithoutId() {
        return aBus().withoutId().build();
    }

    public static BusTransport busWithoutBusNumber() {
        return aBus().withoutBusNumber().build();
    }

    public BusTransport build() {
        BusTransport bus = new BusTransport();
        bus.setId(id);
        bus.setBusCompany(busCompany);
        bus.setBusNumber(busNumber);
        bus.setProviderName(providerName);
        bus.setDepartureStation(departureStation);
        bus.setArrivalStation(arrivalStation);
        bus.setReferenceNumber(referenceNumber);
        bus.setAdditionalInfo(additionalInfo);
        bus.setVerificationStatus(verificationStatus);
        bus.setCreatedAt(createdAt);
        bus.setUpdatedAt(updatedAt);
        return bus;
    }
}