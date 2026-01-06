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

    // ============================================
    // CREATE (DTO → Entity)
    // ============================================
    @SubclassMapping(source = FlightTransportRequestDto.class, target = FlightTransport.class)
    @SubclassMapping(source = BusTransportRequestDto.class, target = BusTransport.class)
    @SubclassMapping(source = CarTransportRequestDto.class, target = CarTransport.class)
    Transport toEntity(TransportRequestDto dto);

    // ============================================
    // READ (Entity → DTO)
    // ============================================
    @SubclassMapping(source = FlightTransport.class, target = FlightTransportResponseDto.class)
    @SubclassMapping(source = BusTransport.class, target = BusTransportResponseDto.class)
    @SubclassMapping(source = CarTransport.class, target = CarTransportResponseDto.class)
    @Mapping(target = "transportType", source = "type")
    TransportResponseDto toDto(Transport entity);

    TransportResponseDtoNonVerbose toDtoNonVersbose(Transport entity);

    // ============================================
    // UPDATE (DTO → Entity)
    // ============================================
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    FlightTransport updateFlightFromDtoAndReturn(FlightTransportRequestDto dto, @MappingTarget FlightTransport entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    BusTransport updateBusFromDtoAndReturn(BusTransportRequestDto dto, @MappingTarget BusTransport entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CarTransport updateCarFromDtoAndReturn(CarTransportRequestDto dto, @MappingTarget CarTransport entity);

    // ============================================
    // Manual dispatcher for updates
    // ============================================
    default Transport updateEntityFromDtoAndReturn(TransportRequestDto dto, Transport entity) {
        return switch (dto) {
            case FlightTransportRequestDto flightDto when entity instanceof FlightTransport flight ->
                    updateFlightFromDtoAndReturn(flightDto, flight);
            case BusTransportRequestDto busDto when entity instanceof BusTransport bus ->
                    updateBusFromDtoAndReturn(busDto, bus);
            case CarTransportRequestDto carDto when entity instanceof CarTransport car ->
                    updateCarFromDtoAndReturn(carDto, car);
            default -> throw new IllegalArgumentException(
                    "Incompatible Dto and Entity types: " +
                            dto.getClass().getName() + " and " + entity.getClass().getName()
            );
        };
    }

    @ObjectFactory
    default Transport createTransport(TransportRequestDto dto) {
        return switch (dto) {
            case FlightTransportRequestDto f -> new FlightTransport();
            case BusTransportRequestDto b -> new BusTransport();
            case CarTransportRequestDto c -> new CarTransport();
            default -> throw new IllegalArgumentException(
                    "Unsupported TransportRequestDto: " + dto.getClass()
            );
        };
    }



}
