package com.koliexpress.tripservice.mapper.transport;

import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDTO;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FlightTransportMapper {

    FlightTransportResponseDTO toResponseDTO(FlightTransport flight);

    @Mapping(target = "verificationStatus", constant = "PENDING")
    FlightTransport toEntity(FlightTransportRequestDTO dto);
}
