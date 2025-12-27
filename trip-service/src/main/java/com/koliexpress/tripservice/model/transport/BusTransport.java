// =================
// Transport by Bus
// =================
package com.koliexpress.tripservice.model.transport;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("BUS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusTransport extends Transport {

    @Column(name = "bus_company")
    private String busCompany;

    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "departure_station")
    private String departureStation;

    @Column(name = "arrival_station")
    private String arrivalStation;

    @Override
    public boolean validate() {
        return busCompany != null && !busCompany.isEmpty();
    }

    @Override
    public String getSummary() {
        return String.format("Bus %s - %s from %s to %s",
                busCompany, busNumber, departureStation, arrivalStation);
    }
}

