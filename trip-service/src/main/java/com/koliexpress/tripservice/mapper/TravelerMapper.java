package com.koliexpress.tripservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDto;
import com.koliexpress.tripservice.dto.TravelerRequestDto;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDto;
import com.koliexpress.tripservice.model.Traveler;

@Mapper(
    componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TravelerMapper {

    TravelerDetailResponseDto toDto(Traveler traveler);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trips", ignore = true)
    Traveler toEntity(TravelerRequestDto request);

    @Mapping(target = "name", expression = "java(traveler.getFirstName() + \" \" + traveler.getLastName())")
    @Mapping(target = "contact", expression = "java(traveler.getEmail() + \" / \" + traveler.getPhoneNumber())")
    @Mapping(target = "activeTripCount", expression = "java(traveler.getTrips() != null ? (int) traveler.getTrips().stream().filter(t -> t.isActive()).count() : 0)")
    TravelerSummaryResponseDto toSummaryDto(Traveler traveler);

}
