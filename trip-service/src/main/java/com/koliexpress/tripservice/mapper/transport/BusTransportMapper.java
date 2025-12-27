package com.koliexpress.tripservice.mapper.transport;


import com.koliexpress.tripservice.dto.transport.BusTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.BusTransportResponseDTO;
import com.koliexpress.tripservice.model.transport.BusTransport;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BusTransportMapper {

    BusTransportResponseDTO toResponseDTO(BusTransport busTransport);

    BusTransport toEntity(BusTransportRequestDTO busTransportRequestDTO);
}
