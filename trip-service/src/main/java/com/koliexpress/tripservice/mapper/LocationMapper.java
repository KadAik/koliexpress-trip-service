package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.dto.LocationRequestDTO;
import com.koliexpress.tripservice.dto.LocationResponseDTO;
import com.koliexpress.tripservice.valueobjects.Location;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface LocationMapper {


    Location toEntity(LocationRequestDTO dto);

    LocationResponseDTO toDTO(Location location);

    void updateEntityFromDTO(LocationRequestDTO dto, @MappingTarget Location location);

    Location updateEntityFromDtoAndReturn(LocationRequestDTO dto, @MappingTarget Location location);
}
