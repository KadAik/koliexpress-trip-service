package com.koliexpress.tripservice.mapper.transport;

import com.koliexpress.tripservice.dto.transport.CarTransportRequestDto;
import com.koliexpress.tripservice.dto.transport.CarTransportResponseDto;
import com.koliexpress.tripservice.model.transport.CarTransport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CarTransportMapper {

    @Mapping(target = "transportType", constant = "CAR")
    CarTransportResponseDto toResponseDto(CarTransport car);

    @Mapping(target = "verificationStatus", constant = "PENDING")
    CarTransport toEntity(CarTransportRequestDto car);
}
