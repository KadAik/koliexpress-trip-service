package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.model.transport.Transport;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link Transport}
 */
@Data
public class TransportRequestDTO implements Serializable {
    @NotNull
    @NotEmpty
    @JsonProperty("provider_name")
    String providerName;

    @JsonProperty("reference_number")
    String referenceNumber;

    @JsonProperty("additional_info")
    String additionalInfo;
}