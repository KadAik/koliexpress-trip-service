package com.koliexpress.tripservice.mapper.transport;


import com.koliexpress.tripservice.dto.transport.*;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.mapper.LocationMapper;
import com.koliexpress.tripservice.model.transport.BusTransport;
import com.koliexpress.tripservice.model.transport.CarTransport;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import com.koliexpress.tripservice.model.transport.Transport;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                FlightTransportMapper.class,
                BusTransportMapper.class,
                CarTransportMapper.class,
        }
)
public interface TransportMapper {

    @SubclassMapping(source = FlightTransport.class, target = FlightTransportResponseDTO.class)
    @SubclassMapping(source = BusTransport.class, target = BusTransportResponseDTO.class)
    @SubclassMapping(source = CarTransport.class, target = CarTransportResponseDTO.class)
    @Mapping(target = "transportType", source = "type")
    TransportResponseDTO toDto(Transport entity);
}
