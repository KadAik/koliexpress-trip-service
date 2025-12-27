package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportResponseDTO implements Serializable {
    UUID id;

    @JsonProperty("provider_name")
    String providerName;

    @JsonProperty("reference_number")
    String referenceNumber;

    @JsonProperty("transport_verification")
    TransportVerificationStatus verificationStatus;

    @JsonProperty("additional_info")
    String additionalInfo;
}
