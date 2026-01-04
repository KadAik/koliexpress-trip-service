package com.koliexpress.tripservice.mapper.transport;


import com.koliexpress.tripservice.dto.transport.*;
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

    @SubclassMapping(source = FlightTransport.class, target = FlightTransportResponseDto.class)
    @SubclassMapping(source = BusTransport.class, target = BusTransportResponseDto.class)
    @SubclassMapping(source = CarTransport.class, target = CarTransportResponseDto.class)
    @Mapping(target = "transportType", source = "type")
    TransportResponseDto toDto(Transport entity);

    @SubclassMapping(source = FlightTransportRequestDto.class, target = FlightTransport.class)
    @SubclassMapping(source = BusTransportRequestDto.class, target = BusTransport.class)
    @SubclassMapping(source = CarTransportRequestDto.class, target = CarTransport.class)
    Transport toEntity(TransportRequestDto dto);


    @ObjectFactory
    default Transport createTransport(TransportRequestDto dto) {
        return switch (dto) {
            case FlightTransportRequestDto f -> new FlightTransport();
            case BusTransportRequestDto b -> new BusTransport();
            case CarTransportRequestDto c -> new CarTransport();
            default ->
                    throw new IllegalArgumentException(
                            "Unsupported TransportRequestDto: " + dto.getClass()
                    );
        };
    }

}
