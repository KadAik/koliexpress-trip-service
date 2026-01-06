package com.koliexpress.tripservice.mapper.transport;


import com.koliexpress.tripservice.dto.transport.BusTransportRequestDto;
import com.koliexpress.tripservice.dto.transport.BusTransportResponseDto;
import com.koliexpress.tripservice.model.transport.BusTransport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BusTransportMapper {

    @Mapping(target = "transportType", constant = "BUS")
    BusTransportResponseDto toResponseDto(BusTransport busTransport);

    @Mapping(target = "verificationStatus", constant = "PENDING")
    BusTransport toEntity(BusTransportRequestDto busTransportRequestDto);
}
