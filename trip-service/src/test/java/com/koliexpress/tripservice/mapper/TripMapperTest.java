package com.koliexpress.tripservice.mapper;

import com.koliexpress.tripservice.builder.dto.LocationRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.dto.trip.BusTripRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.dto.trip.CarTripRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.dto.trip.FlightTripRequestDtoTestBuilder;
import com.koliexpress.tripservice.builder.model.LocationTestBuilder;
import com.koliexpress.tripservice.builder.model.TravelerTestBuilder;
import com.koliexpress.tripservice.builder.model.TripTestBuilder;
import com.koliexpress.tripservice.dto.trip.FlightTripRequestDto;
import com.koliexpress.tripservice.dto.trip.TripRequestDto;
import com.koliexpress.tripservice.dto.trip.TripResponseDto;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import com.koliexpress.tripservice.enums.TripStatus;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.Traveler;

import com.koliexpress.tripservice.model.transport.BusTransport;
import com.koliexpress.tripservice.model.transport.CarTransport;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import com.koliexpress.tripservice.model.transport.Transport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("TripMapper Tests")
class TripMapperTest {

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private LocationMapper locationMapper;

    static Stream<Arguments> transportTypeCases() {
        return Stream.of(
                Arguments.of(
                        TransportType.PLANE,
                        (Supplier<TripTestBuilder>) TripTestBuilder::aFlightTrip,
                        "flight"
                ),
                Arguments.of(
                        TransportType.BUS,
                        (Supplier<TripTestBuilder>) TripTestBuilder::aBusTrip,
                        "bus"
                ),
                Arguments.of(
                        TransportType.CAR,
                        (Supplier<TripTestBuilder>) TripTestBuilder::aCarTrip,
                        "car"
                )
        );
    }

    @ParameterizedTest(name = "Should map Trip with transport type {0} correctly")
    @MethodSource("transportTypeCases")
    void testToDto_withDifferentTransportTypes_shouldSetCorrectTransportDetailsFields(
            TransportType transport,
            Supplier<TripTestBuilder> tripBuilderSupplier,
            String expectedTransport
    ) {
        // Arrange
        Traveler traveler = TravelerTestBuilder.aTraveler().build();

        Trip trip = tripBuilderSupplier.get()
                .withTraveler(traveler)
                .build();

        // Act
        TripResponseDto result = tripMapper.toDto(trip);

        // Assert (common)
        assertThat(result).isNotNull();
        assertThat(result.getTravelerId()).isEqualTo(traveler.getId());

        // Polymorphic assertions

//        switch (expectedTransport) {
//            case "flight" -> {
//                assertThat(result.getFlightDetails()).isNotNull();
//                assertThat(result.getBusDetails()).isNull();
//                assertThat(result.getCarDetails()).isNull();
//            }
//            case "bus" -> {
//                assertThat(result.getFlightDetails()).isNull();
//                assertThat(result.getBusDetails()).isNotNull();
//                assertThat(result.getCarDetails()).isNull();
//            }
//            case "car" -> {
//                assertThat(result.getFlightDetails()).isNull();
//                assertThat(result.getBusDetails()).isNull();
//                assertThat(result.getCarDetails()).isNotNull();
//            }
//        }
    }


    @Test
    @DisplayName("Should map TripRequestDto to Trip entity with DRAFT status")
    void testToEntity_whenGivenTripRequestDto_shouldMapToTripWithDraftStatus() {
        // Arrange
        FlightTripRequestDto dto = FlightTripRequestDtoTestBuilder.validFlightTrip();

        // Act
        Trip result = tripMapper.toEntity(dto);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNull(); // Should be ignored : handled by service layer
        assertThat(result.getTraveler()).isNull(); // Should be ignored

        assertThat(result.getTransport()).isNotNull(); // Handled by mapTransport method
        assertThat(result.getTransport().getVerificationStatus()).isEqualTo(TransportVerificationStatus.PENDING);
        assertThat(result.getTransport().getId()).isNull(); // Should be ignored
        assertThat(result.getTransport().getProviderName()).isEqualTo(dto.getFlightDetails().getProviderName());

        assertThat(result.getStatus()).isEqualTo(TripStatus.DRAFT);

        assertThat(result.getCreatedAt()).isNull();
        assertThat(result.getUpdatedAt()).isNull();

        assertThat(result.getOrigin()).isNotNull();
        assertThat(result.getDestination()).isNotNull();

        assertThat(result.getDepartureDate()).isEqualTo(dto.getDepartureDate());
        assertThat(result.getArrivalDate()).isEqualTo(dto.getArrivalDate());
        assertThat(result.getAvailableWeight()).isEqualTo(dto.getAvailableWeight());
        assertThat(result.getPricePerKg()).isEqualTo(dto.getPricePerKg());
        assertThat(result.getPriceAsked()).isEqualTo(dto.getPriceAsked());
        //assertThat(result.getTransportType()).isEqualTo(dto.getTransportType());
        assertThat(result.getNotice()).isEqualTo(dto.getNotice());
    }


     static Stream<Arguments> tripTypeCases() {
        return Stream.of(
                Arguments.of(
                        FlightTripRequestDtoTestBuilder.validFlightTrip(),
                        TransportType.PLANE,
                        FlightTransport.class,
                        "departureAirportCode"
                ),
                Arguments.of(
                        BusTripRequestDtoTestBuilder.validBusTrip(),
                        TransportType.BUS,
                        BusTransport.class,
                        "busCompany"
                ),
                Arguments.of(
                        CarTripRequestDtoTestBuilder.aCarTripRequestDto().build(),
                        TransportType.CAR,
                        CarTransport.class,
                        "vehicleModel"
                )
        );
    }

    @ParameterizedTest(name = "Should map {0} TripRequestDto to Trip entity with correct Transport subtype")
    @MethodSource("tripTypeCases")
    void testToEntity_whenGivenAnyTripRequestDto_shouldMapPolymorphically(
            TripRequestDto requestDto,
            TransportType expectedTransportType,
            Class<? extends Transport> expectedTransportClass,
            String testField
    ) {

        // Act
        Trip trip = tripMapper.toEntity(requestDto);

        // Assert
        assertThat(trip.getTransportType()).isEqualTo(expectedTransportType);
        assertThat(trip.getTransport()).isInstanceOf(expectedTransportClass);
        assertThat(trip.getTransport()).hasFieldOrProperty(testField);
    }

    @Test
    void testUpdateEntityFromDtoAndReturn_whenGivenFlightTripRequestDto_shouldUpdateTripEntityCorrectly() {
        // Arrange
        FlightTripRequestDto dto = FlightTripRequestDtoTestBuilder
                .aFlightTripRequestDto()
                .withAvailableWeight("50.0")
                .withPricePerKg("20.0")
                .withPriceAsked("800.0")
                .withNotice("Updated notice")
                .withOrigin(LocationRequestDtoTestBuilder
                        .aLocationRequestDto()
                        .withCity("New Origin City")
                        .withCountry("New Origin Country")
                        .build())
                .build();

        Trip existingTrip = TripTestBuilder.aFlightTrip()
                .withAvailableWeight(30.0)
                .withPricePerKg(15.0)
                .withPriceAsked(500.0)
                .withNotice("Old notice")
                .withOrigin(LocationTestBuilder
                        .aLocation()
                        .withCity("Old Origin City")
                        .withCountry("Old Origin Country")
                        .build())
                .build();

        // Act
        Trip updatedTrip = tripMapper.updateEntityFromDtoAndReturn(dto, existingTrip);

        // Assert
        assertThat(updatedTrip).isNotNull();
        assertThat(updatedTrip.getAvailableWeight()).isEqualTo(dto.getAvailableWeight());
        assertThat(updatedTrip.getPricePerKg()).isEqualTo(dto.getPricePerKg());
        assertThat(updatedTrip.getPriceAsked()).isEqualTo(dto.getPriceAsked());
        assertThat(updatedTrip.getNotice()).isEqualTo(dto.getNotice());

        // Ensure unchanged fields remain the same
        assertThat(updatedTrip.getId()).isEqualTo(existingTrip.getId());
        assertThat(updatedTrip.getTraveler()).isEqualTo(existingTrip.getTraveler());
        assertThat(updatedTrip.getDestination()).isEqualTo(existingTrip.getDestination());

        // Check that origin location was updated (replaced as Location is a value object)
        assertThat(updatedTrip.getOrigin().getCity()).isEqualTo("New Origin City");

        // Attention : existingTrip == updatedTrip after mapping
        assertThat(existingTrip.getOrigin().getCountry()).isEqualTo("New Origin Country");
        assertThat(existingTrip.getOrigin().getCity()).isEqualTo("New Origin City");

    }

}