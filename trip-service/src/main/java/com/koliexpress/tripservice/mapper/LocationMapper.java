package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.dto.LocationRequestDto;
import com.koliexpress.tripservice.dto.LocationResponseDto;
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
    // Notice : Location is a value objetc and is not intented to be mutated after creation.
    // Therefore, we do not provide an update method here.

    Location toEntity(LocationRequestDto dto);

    LocationResponseDto toDto(Location location);
}
