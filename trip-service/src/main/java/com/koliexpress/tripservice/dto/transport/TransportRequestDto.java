package com.koliexpress.tripservice.dto.transport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.model.transport.Transport;
import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

/**
 * Dto for {@link Transport}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "transport_type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FlightTransportRequestDto.class, name = "PLANE"),
        @JsonSubTypes.Type(value = BusTransportRequestDto.class, name = "BUS"),
        @JsonSubTypes.Type(value = CarTransportRequestDto.class, name = "CAR")
})
public abstract class TransportRequestDto implements Serializable {

    @NotNull(message = "Transport type is required", groups = {ValidationGroups.Create.class, ValidationGroups.Update.class})
    @JsonProperty("transport_type")
    private TransportType type;

    @NotBlank(
            message = "Provider name is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 100, message = "Provider name cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("provider_name")
    private String providerName;

    @Size(
            max = 50, message = "Reference number cannot exceed 50 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("reference_number")
    private String referenceNumber;

    @Size(
            max = 500, message = "Additional info cannot exceed 500 characters",
            groups = {ValidationGroups.Create.class, }
    )
    @JsonProperty("additional_info")
    private String additionalInfo;
}