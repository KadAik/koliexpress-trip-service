package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.enums.TransportType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusTransportResponseDTO extends TransportResponseDTO {

    UUID id;

    @JsonProperty("transport_type")
    TransportType transportType = TransportType.BUS;

    @JsonProperty("bus_company")
    String busCompany;

    @JsonProperty("bus_number")
    String busNumber;

    @JsonProperty("departure_station")
    String departureStation;

    @JsonProperty("arrival_station")
    String arrivalStation;

    @JsonProperty("additional_info")
    String additionalInfo;
}
