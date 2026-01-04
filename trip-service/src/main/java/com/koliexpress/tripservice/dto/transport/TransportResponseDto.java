package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "transportType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FlightTransportResponseDto.class, name = "PLANE"),
        @JsonSubTypes.Type(value = BusTransportResponseDto.class, name = "BUS"),
        @JsonSubTypes.Type(value = CarTransportResponseDto.class, name = "CAR")
})
public class TransportResponseDto implements Serializable {
    UUID id;

    @JsonProperty("transport_type")
    TransportType transportType;

    @JsonProperty("provider_name")
    String providerName;

    @JsonProperty("reference_number")
    String referenceNumber;

    @JsonProperty("transport_verification")
    TransportVerificationStatus verificationStatus;

    @JsonProperty("additional_info")
    String additionalInfo;
}
