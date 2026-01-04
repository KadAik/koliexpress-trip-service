package com.koliexpress.tripservice.service;

import com.koliexpress.tripservice.builder.dto.trip.FlightTripRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.model.TravelerTestBuilder;
import com.koliexpress.tripservice.builder.model.TripTestBuilder;
import com.koliexpress.tripservice.dto.trip.FlightTripRequestDto;
import com.koliexpress.tripservice.dto.trip.TripResponseDto;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.mapper.TripMapper;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.repository.TravelerRepository;
import com.koliexpress.tripservice.repository.TripRepository;
import com.koliexpress.tripservice.service.impl.TripServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private TravelerRepository travelerRepository;

    @Mock
    private TripMapper tripMapper;

    @InjectMocks
    private TripServiceImpl tripService;

    private UUID travelerId;
    private Traveler traveler;

    @BeforeEach
    void setUp() {
        travelerId = UUID.randomUUID();
        traveler = TravelerTestBuilder
                .aTraveler()
                .withId(travelerId)
                .build();
    }

    @Test
    void testGetAllTrips_whenTripsExist_shouldReturnListOfTripsWithImportantFields(){

        TripResponseDto dto_1 = TripResponseDto
                .builder().build();


        given(tripRepository.findAll())
                .willReturn(List.of());

        List<TripResponseDto> trips = tripService.getAllTrips();
    }

    @Test
    void testCreateTrip_whenValidFlightTripRequest_shouldCreateFlightTrip() {
        // Arrange
        FlightTripRequestDto request = FlightTripRequestDtoTestBuilder
                .aFlightTripRequestDto()
                .withTravelerId(travelerId)
                .build();

        Trip mappedTrip = TripTestBuilder
                .aFlightTrip()
                .build();

        TripResponseDto expectedResponse = TripResponseDto.builder().build();

        given(travelerRepository.findById(travelerId))
                .willReturn(Optional.of(traveler));
        given(tripMapper.toEntity(request))
                .willReturn(mappedTrip);
        given(tripRepository.save(mappedTrip))
                .willReturn(mappedTrip);
        given(tripMapper.toResponseDto(mappedTrip))
                .willReturn(expectedResponse);

        // Act
        TripResponseDto response = tripService.createTrip(request);

        // Assert
        assertThat(response).isSameAs(expectedResponse);
        assertThat(mappedTrip.getTraveler()).isEqualTo(traveler);

        verify(travelerRepository).findById(travelerId);
        verify(tripMapper).toEntity(request);
        verify(tripRepository).save(mappedTrip);
        verify(tripMapper).toResponseDto(mappedTrip);
    }

    @Test
    void testCreateTrip_whenTravelerNotFound_shouldThrowResourceNotFoundException() {
        // Arrange
        UUID invalidTravelerId = UUID.randomUUID();
        FlightTripRequestDto request = FlightTripRequestDtoTestBuilder
                .aFlightTripRequestDto()
                .withTravelerId(invalidTravelerId)
                .build();

        given(travelerRepository.findById(invalidTravelerId))
                .willReturn(Optional.empty());

        // Act & Assert
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> tripService.createTrip(request))
                .withMessage("Traveler with id "+ invalidTravelerId +" not found");

        verify(travelerRepository).findById(invalidTravelerId);
    }

    @Test
    void testCreateTrip_whenTravelerIdIsNullOrMissing_shouldReturnValidationErrorResponse() {
        // Arrange
        FlightTripRequestDto request = FlightTripRequestDtoTestBuilder
                .aFlightTripRequestDto()
                .withTravelerId(null)
                .build();

        // Act & Assert
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> tripService.createTrip(request))
                .withMessage("Traveler ID must be provided to create a Trip");

    }
}


