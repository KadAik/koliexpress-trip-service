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

    /**
     * Trip Entity → TripResponseDto
     */
    @Mapping(target = "travelerId", source = "traveler.id")
    @Mapping(target = "origin", source = "origin")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "transportDetails", source = "transport")
    TripResponseDto toResponseDto(Trip entity);

    /**
     * TripRequestDto → Trip Entity
     * Ignored fields are handled in the Service layer or auto-generated
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "traveler", ignore = true)
    @Mapping(target = "transport", source = "transport")
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "origin", source = "origin")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "transportType", ignore = true) // handled in service
    Trip toEntity(TripRequestDto dto);

    /**
     * TripRequestDto → Trip Entity
     * Update existing Trip entity with values from Dto
     */

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    void updateEntityFromDto(FlightTripRequestDto dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    void updateEntityFromDto(BusTripRequestDto dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    void updateEntityFromDto(CarTripRequestDto dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(FlightTripRequestDto dto, @MappingTarget Trip trip, @Context LocationMapper locationMapper);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(BusTripRequestDto dto, @MappingTarget Trip trip, @Context LocationMapper locationMapper);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(CarTripRequestDto dto, @MappingTarget Trip trip, @Context LocationMapper locationMapper);

    @AfterMapping
    default void updateLocations(
            FlightTripRequestDto dto,
            @MappingTarget Trip trip,
            @Context LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    @AfterMapping
    default void updateLocations(
            BusTripRequestDto dto,
            @MappingTarget Trip trip,
            @Context LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    @AfterMapping
    default void updateLocations(
            CarTripRequestDto dto,
            @MappingTarget Trip trip,
            @Context LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    private void updateLocationsInternal(
            TripRequestDto dto,
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

    default FlightTransportResponseDto mapFlightDetails(Trip trip) {
        return trip.getTransport().getType() == TransportType.PLANE
                ? map((FlightTransport) trip.getTransport())
                : null;
    }

    default BusTransportResponseDto mapBusDetails(Trip trip) {
        return trip.getTransport().getType() == TransportType.BUS
                ? map((BusTransport) trip.getTransport())
                : null;
    }

    default CarTransportResponseDto mapCarDetails(Trip trip) {
        return trip.getTransport().getType() == TransportType.CAR
                ? map((CarTransport) trip.getTransport())
                : null;
    }


    FlightTransportResponseDto map(FlightTransport flightTransport);
    BusTransportResponseDto map(BusTransport busTransport);
    CarTransportResponseDto map(CarTransport carTransport);

    FlightTransport map(FlightTransportRequestDto flightTransportDto);
    BusTransport map(BusTransportRequestDto busTransportDto);
    CarTransport map(CarTransportRequestDto carTransportDto);
}