package com.koliexpress.tripservice.mapper.transport;

import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDto;
import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDto;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FlightTransportMapper {

    @Mapping(target = "transportType", constant = "PLANE")
    FlightTransportResponseDto toResponseDto(FlightTransport flight);

    @Mapping(target = "verificationStatus", constant = "PENDING")
    FlightTransport toEntity(FlightTransportRequestDto dto);
}
