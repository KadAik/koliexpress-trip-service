package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.mapper.transport.TransportMapper;
import com.koliexpress.tripservice.model.Trip;
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
    TripResponseDto toDto(Trip entity);

    @Mapping(target = "travelerId", source = "traveler.id")
    @Mapping(target = "origin", source = "origin")
    @Mapping(target = "destination", source = "destination")
    @Mapping(target = "transport", source = "transport")
    TripResponseDtoNonVerbose toDtoNonVerbose(Trip entity);

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

    @Mapping(target = "origin", ignore = true) // handled by service
    @Mapping(target = "destination", ignore = true)
    @Mapping(target = "transport", source = "transport")
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(TripRequestDto dto, @MappingTarget Trip trip);

    @Mapping(target = "origin", ignore = true) // handled by service
    @Mapping(target = "destination", ignore = true)
    @Mapping(target = "transport", ignore = true) // handled by service
    @Mapping(target = "id", ignore = true)
    Trip updateEntityFromDtoAndReturn(TripRequestDto dto, @MappingTarget Trip trip);
}