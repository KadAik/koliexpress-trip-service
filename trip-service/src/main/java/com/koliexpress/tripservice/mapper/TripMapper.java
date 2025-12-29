package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.dto.transport.BusTransportResponseDTO;
import com.koliexpress.tripservice.dto.transport.CarTransportResponseDTO;
import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDTO;
import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.mapper.transport.BusTransportMapper;
import com.koliexpress.tripservice.mapper.transport.CarTransportMapper;
import com.koliexpress.tripservice.mapper.transport.FlightTransportMapper;
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
                LocationMapper.class
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
    @Mapping(target = "flightDetails", expression = "java(mapFlightDetails(trip))")
    @Mapping(target = "busDetails", expression = "java(mapBusDetails(trip))")
    @Mapping(target = "carDetails", expression = "java(mapCarDetails(trip))")
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
    @Mapping(target = "transport", ignore = true)
    @Mapping(target = "status", constant = "DRAFT")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "origin", source = "origin")
    @Mapping(target = "destination", source = "destination")
    Trip toEntity(TripRequestDTO dto);

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
    Trip updateEntityFromDtoAndReturn(FlightTripRequestDTO dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(BusTripRequestDTO dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true)
    @Mapping(target = "destination", ignore = true)
    Trip updateEntityFromDtoAndReturn(CarTripRequestDTO dto, @MappingTarget Trip trip);

    @AfterMapping
    default void updateLocations(
            FlightTripRequestDTO dto,
            @MappingTarget Trip trip,
            LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    @AfterMapping
    default void updateLocations(
            BusTripRequestDTO dto,
            @MappingTarget Trip trip,
            LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    @AfterMapping
    default void updateLocations(
            CarTripRequestDTO dto,
            @MappingTarget Trip trip,
            LocationMapper locationMapper
    ) {
        updateLocationsInternal(dto, trip, locationMapper);
    }

    private void updateLocationsInternal(
            TripRequestDTO dto,
            Trip trip,
            LocationMapper locationMapper
    ) {
        if (dto.getOrigin() != null) {
            if (trip.getOrigin() == null) {
                trip.setOrigin(locationMapper.toEntity(dto.getOrigin()));
            } else {
                locationMapper.updateEntityFromDTO(dto.getOrigin(), trip.getOrigin());
            }
        }

        if (dto.getDestination() != null) {
            if (trip.getDestination() == null) {
                trip.setDestination(locationMapper.toEntity(dto.getDestination()));
            } else {
                locationMapper.updateEntityFromDTO(dto.getDestination(), trip.getDestination());
            }
        }
    }


    // ============================================
    // POLYMORPHIC TRANSPORT MAPPING
    // ============================================

    default FlightTransportResponseDTO mapFlightDetails(Trip trip) {
        return trip.getTransport() instanceof FlightTransport flight
                ? map(flight)
                : null;
    }

    default BusTransportResponseDTO mapBusDetails(Trip trip) {
        return trip.getTransport() instanceof BusTransport bus
                ? map(bus)
                : null;
    }

    default CarTransportResponseDTO mapCarDetails(Trip trip) {
        return trip.getTransport() instanceof CarTransport car
                ? map(car)
                : null;
    }

    FlightTransportResponseDTO map(FlightTransport flightTransport);
    BusTransportResponseDTO map(BusTransport busTransport);
    CarTransportResponseDTO map(CarTransport carTransport);
}