package com.koliexpress.tripservice.mapper.transport;

import com.koliexpress.tripservice.dto.transport.CarTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.CarTransportResponseDTO;
import com.koliexpress.tripservice.model.transport.CarTransport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CarTransportMapper {

    CarTransportResponseDTO toResponseDTO(CarTransport car);

    CarTransport toEntity(CarTransportRequestDTO car);
}
