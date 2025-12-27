package com.koliexpress.tripservice.mapper.transport;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDTO;
import com.koliexpress.tripservice.dto.TravelerRequestDTO;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDTO;
import com.koliexpress.tripservice.model.Traveler;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE
)
public interface TravelerMapper {

    @Mapping(target = "tripIds", expression = "java(traveler.getTrips().stream().map(trip -> trip.getId()).toList())")
    TravelerDetailResponseDTO toDetailResponseDTO(Traveler traveler);

    TravelerSummaryResponseDTO toSummaryResponseDTO(Traveler traveler);

    Traveler toEntity(TravelerRequestDTO traveler);
}
