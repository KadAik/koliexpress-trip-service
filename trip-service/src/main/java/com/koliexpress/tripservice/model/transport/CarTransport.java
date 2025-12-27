// =================
// Transport by Car
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
@DiscriminatorValue("CAR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarTransport extends Transport {

    @Column(name = "vehicle_make")
    private String vehicleMake;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "vehicle_year")
    private Integer vehicleYear;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "vehicle_color")
    private String vehicleColor;

    @Override
    public boolean validate() {
        return vehicleMake != null && !vehicleMake.isEmpty()
                && licensePlate != null && !licensePlate.isEmpty();
    }

    @Override
    public String getSummary() {
        return String.format("%s %s (%s) - %s",
                vehicleYear, vehicleMake, vehicleModel, licensePlate);
    }
}
