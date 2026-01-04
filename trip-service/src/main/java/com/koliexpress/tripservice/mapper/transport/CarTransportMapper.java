package com.koliexpress.tripservice.mapper.transport;

import com.koliexpress.tripservice.dto.transport.CarTransportRequestDto;
import com.koliexpress.tripservice.dto.transport.CarTransportResponseDto;
import com.koliexpress.tripservice.model.transport.CarTransport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CarTransportMapper {

    CarTransportResponseDto toResponseDto(CarTransport car);

    CarTransport toEntity(CarTransportRequestDto car);
}
