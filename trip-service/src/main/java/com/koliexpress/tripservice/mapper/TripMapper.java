package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.dto.transport.*;
import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.mapper.transport.BusTransportMapper;
import com.koliexpress.tripservice.mapper.transport.CarTransportMapper;
import com.koliexpress.tripservice.mapper.transport.FlightTransportMapper;
import com.koliexpress.tripservice.mapper.transport.TransportMapper;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.transport.*;
import org.hibernate.Hibernate;
import org.mapstruct.*;

/**
 * MapStruct mapper for Trip
 * Implementation is auto-generated at compile time
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, // preserve existing values on updates
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                FlightTransportMapper.class,
                BusTransportMapper.class,
                CarTransportMapper.class,
                LocationMapper.class,
                TransportMapper.class
        }
)
public interface TripMapper {

    // ============================================
    // RESPONSE MAPPING (Entity → DTO)
    // ============================================
    /**
     * Trip Entity → TripResponseDTO
     */
    @Mapping(target = "travelerId", source = "traveler.id")
    @Mapping(target = "origin", source = "origin")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "transportDetails", source = "transport")
    TripResponseDTO toResponseDTO(Trip trip);

    // ============================================
    // REQUEST MAPPING (DTO → Entity)
    // ============================================

    /**
     * TripRequestDTO → Trip Entity
     * Ignored fields are handled in the Service layer or auto-generated
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "traveler", ignore = true)
    @Mapping(target = "transport", expression = "java(mapTransport(dto))")
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "origin", source = "origin")
    @Mapping(target = "destination", source = "destination")
    Trip toEntity(TripRequestDTO dto);

    default Transport mapTransport(TripRequestDTO dto) {
        return switch (dto) {
            case FlightTripRequestDTO flightDto ->
                    map(flightDto.getFlightDetails());
            case BusTripRequestDTO busDto ->
                    map(busDto.getBusDetails());
            case CarTripRequestDTO carDto ->
                    map(carDto.getCarDetails());
            default ->
                    throw new IllegalArgumentException(
                            "Unknown TripRequestDTO type: " + dto.getClass().getName()
                    );
        };
    }


    /**
     * TripRequestDTO → Trip Entity
     * Update existing Trip entity with values from DTO
     */

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    void updateEntityFromDTO(FlightTripRequestDTO dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    void updateEntityFromDTO(BusTripRequestDTO dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    void updateEntityFromDTO(CarTripRequestDTO dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(FlightTripRequestDTO dto, @MappingTarget Trip trip, @Context LocationMapper locationMapper);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(BusTripRequestDTO dto, @MappingTarget Trip trip, @Context LocationMapper locationMapper);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(CarTripRequestDTO dto, @MappingTarget Trip trip, @Context LocationMapper locationMapper);

    @AfterMapping
    default void updateLocations(
            FlightTripRequestDTO dto,
            @MappingTarget Trip trip,
            @Context LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    @AfterMapping
    default void updateLocations(
            BusTripRequestDTO dto,
            @MappingTarget Trip trip,
            @Context LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    @AfterMapping
    default void updateLocations(
            CarTripRequestDTO dto,
            @MappingTarget Trip trip,
            @Context LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    private void updateLocationsInternal(
            TripRequestDTO dto,
            Trip trip,
            LocationMapper locationMapper
    ) {
        if (dto.getOrigin() != null) {
            // Location is a value object -> replace entirely.
            trip.setOrigin(locationMapper.toEntity(dto.getOrigin()));
        }

        if (dto.getDestination() != null) {
            trip.setDestination(locationMapper.toEntity(dto.getDestination()));
        }
    }


    // ============================================
    // POLYMORPHIC TRANSPORT MAPPING
    // ============================================

    default FlightTransportResponseDTO mapFlightDetails(Trip trip) {
        return trip.getTransport().getType() == TransportType.PLANE
                ? map((FlightTransport) trip.getTransport())
                : null;
    }

    default BusTransportResponseDTO mapBusDetails(Trip trip) {
        return trip.getTransport().getType() == TransportType.BUS
                ? map((BusTransport) trip.getTransport())
                : null;
    }

    default CarTransportResponseDTO mapCarDetails(Trip trip) {
        return trip.getTransport().getType() == TransportType.CAR
                ? map((CarTransport) trip.getTransport())
                : null;
    }


    FlightTransportResponseDTO map(FlightTransport flightTransport);
    BusTransportResponseDTO map(BusTransport busTransport);
    CarTransportResponseDTO map(CarTransport carTransport);

    FlightTransport map(FlightTransportRequestDTO flightTransportDto);
    BusTransport map(BusTransportRequestDTO busTransportDto);
    CarTransport map(CarTransportRequestDTO carTransportDto);
}