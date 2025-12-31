package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.koliexpress.tripservice.model.transport.Transport;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link Transport}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TransportRequestDTO implements Serializable {

    @NotBlank(
            message = "Provider name is required",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @Size(
            max = 100, message = "Provider name cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("provider_name")
    private String providerName;

    @Size(
            max = 50, message = "Reference number cannot exceed 50 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("reference_number")
    private String referenceNumber;

    @Size(
            max = 500, message = "Additional info cannot exceed 500 characters",
            groups = {ValidationGroups.Create.class, ValidationGroups.Update.class}
    )
    @JsonProperty("additional_info")
    private String additionalInfo;
}