package com.koliexpress.tripservice.service;

import com.koliexpress.tripservice.builder.dto.trip.FlightTripRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.model.TravelerTestBuilder;
import com.koliexpress.tripservice.builder.model.TripTestBuilder;
import com.koliexpress.tripservice.dto.LocationResponseDto;
import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDto;
import com.koliexpress.tripservice.dto.transport.TransportResponseDto;
import com.koliexpress.tripservice.dto.transport.TransportResponseDtoNonVerbose;
import com.koliexpress.tripservice.dto.trip.FlightTripRequestDto;
import com.koliexpress.tripservice.dto.trip.TripResponseDto;
import com.koliexpress.tripservice.dto.trip.TripResponseDtoNonVerbose;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
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

        Trip trip = TripTestBuilder
                .aFlightTrip()
                .build();

        TripResponseDtoNonVerbose dto = TripResponseDtoNonVerbose
                .builder()
                .id(trip.getId())
                .url(trip.getUrl())
                .origin(LocationResponseDto
                        .builder()
                        .name("Lagos")
                        .build())
                .destination(LocationResponseDto
                        .builder()
                        .name("Accra")
                        .build())
                .transportType(trip.getTransportType())
                .transport(TransportResponseDtoNonVerbose
                        .builder()
                        .url(trip.getUrl())
                        .type(trip.getTransportType())
                        .id(trip.getTransport().getId())
                        .build())
                .build();

        given(tripRepository.findAll())
                .willReturn(List.of(trip));
        given(tripMapper.toDtoNonVerbose(trip))
                .willReturn(dto);

        // when
        List<TripResponseDtoNonVerbose> trips = tripService.getAllTrips();

        // then
        assertThat(trips).isNotNull().hasSize(1);

        TripResponseDtoNonVerbose result = trips.get(0);

        assertThat(result.getId()).isEqualTo(trip.getId());
        assertThat(result.getOrigin()).isNotNull();
        assertThat(result.getDestination()).isNotNull();
        assertThat(result.getTransport()).isNotNull();
        assertThat(result.getTransportType()).isNotNull();
        assertThat(result).hasFieldOrProperty("url");
        assertThat(result.getUrl()).isNotNull();

        TransportResponseDtoNonVerbose transport = result.getTransport();
        assertThat(transport)
                .extracting("id", "url")
                .doesNotContainNull();

        verify(tripRepository).findAll();
        verify(tripMapper).toDtoNonVerbose(trip);

    }

    @Test
    void testGetTripById_whenTripExists_shouldReturnATripWithAllFields(){

        String tripId = UUID.randomUUID().toString();

        Trip trip = TripTestBuilder
                .aFlightTrip()
                .build();

        TripResponseDto dto = TripResponseDto
                .builder()
                .id(UUID.fromString(tripId))
                .origin(LocationResponseDto.builder().build())
                .destination(LocationResponseDto.builder().build())
                .transportType(trip.getTransportType())
                .transportDetails(FlightTransportResponseDto
                        .builder()
                        .providerName("Delta Airlines")
                        .flightNumber("DEL01")
                        .transportType(trip.getTransportType())
                        .build())
                .build();

        given(tripRepository.findByIdWithTransport(UUID.fromString(tripId)))
                .willReturn(Optional.of(trip));

        given(tripMapper.toDto(trip))
                .willReturn(dto);

        // When
        TripResponseDto response = tripService.getTripById(tripId);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(UUID.fromString(tripId));
        assertThat(response.getOrigin()).isNotNull();
        assertThat(response.getDestination()).isNotNull();
        assertThat(response.getTransportDetails()).isNotNull();

        TransportResponseDto transport = response.getTransportDetails();

        assertThat(transport.getProviderName()).isNotNull();
        assertThat(transport.getTransportType()).isNotNull();
        assertThat(transport).hasFieldOrProperty("flightNumber");
        assertThat(transport).extracting("flightNumber").isNotNull();

        verify(tripRepository).findByIdWithTransport(UUID.fromString(tripId));
        verify(tripMapper).toDto(any(Trip.class));

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
        given(tripMapper.toDto(mappedTrip))
                .willReturn(expectedResponse);

        // Act
        TripResponseDto response = tripService.createTrip(request);

        // Assert
        assertThat(response).isSameAs(expectedResponse);
        assertThat(mappedTrip.getTraveler()).isEqualTo(traveler);

        verify(travelerRepository).findById(travelerId);
        verify(tripMapper).toEntity(request);
        verify(tripRepository).save(mappedTrip);
        verify(tripMapper).toDto(mappedTrip);
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


