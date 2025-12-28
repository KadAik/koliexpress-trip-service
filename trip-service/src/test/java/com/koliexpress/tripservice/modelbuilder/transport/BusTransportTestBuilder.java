package com.koliexpress.tripservice.modelbuilder.transport;

import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import com.koliexpress.tripservice.model.transport.BusTransport;
import lombok.Builder;

import java.util.UUID;

public class BusTransportTestBuilder {

    private UUID id = UUID.randomUUID();
    private String busCompany = "ABC Transport";
    private String busNumber = "BUS-789";
    private String referenceNumber = "REF-456";
    private String providerName = "ABC Transport";
    private String departureStation = "CMS Terminal Lagos";
    private String arrivalStation = "Jibowu Terminal Lagos";
    private TransportVerificationStatus verificationStatus = TransportVerificationStatus.PENDING;

    private BusTransportTestBuilder() {}

    public static BusTransportTestBuilder aBus() {
        return new BusTransportTestBuilder();
    }

    public BusTransportTestBuilder withBusCompany(String company) {
        this.busCompany = company;
        return this;
    }
    public BusTransportTestBuilder withBusNumber(String number) {
        this.busNumber = number;
        return this;
    }
    public BusTransportTestBuilder withProviderName(String provider) {
        this.providerName = provider;
        return this;
    }
    public BusTransportTestBuilder withDepartureStation(String station) {
        this.departureStation = station;
        return this;
    }

    public BusTransportTestBuilder withArrivalStation(String station) {
        this.arrivalStation = station;
        return this;
    }

    public BusTransportTestBuilder withVerificationStatus(TransportVerificationStatus status) {
        this.verificationStatus = status;
        return this;
    }

    public BusTransportTestBuilder withoutID() {
        this.id = null;
        return this;
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
        bus.setVerificationStatus(verificationStatus);
        return bus;
    }
}