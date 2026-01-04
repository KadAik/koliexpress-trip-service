package com.koliexpress.tripservice.controller;

import com.koliexpress.tripservice.builder.dto.trip.FlightTripRequestDtoTestBuilder;
import com.koliexpress.tripservice.dto.trip.FlightTripRequestDTO;
import com.koliexpress.tripservice.dto.trip.TripRequestDTO;
import com.koliexpress.tripservice.dto.trip.TripResponseDTO;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    TripService tripService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTrips_whenTripsExist_shouldReturnListOfTrips() throws Exception {
        // Arrange

        TripResponseDTO dto_1 = TripResponseDTO
                .builder()
                .build();
        TripResponseDTO dto_2 = TripResponseDTO
                .builder()
                .build();

        given(tripService.getAllTrips())
                .willReturn(List.of(dto_1, dto_2));

        // Act & Assert
        mvc.perform(get("/api/v1/trips"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));


    }

    @Test
    void testGetAllTrips_whenNoTripsExist_shouldReturnEmptyList() throws Exception {
        // Arrange
        given(tripService.getAllTrips())
                .willReturn(List.of());

        // Act & Assert
        mvc.perform(get("/api/v1/trips"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testGetTripById_whenTripExists_shouldReturnTrip() throws Exception {
        // Given
        String tripId = "123e4567-e89b-12d3-a456-426614174000";
        TripResponseDTO dto = TripResponseDTO
                .builder()
                .id(UUID.fromString(tripId))
                .build();

        given(tripService.getTripById(tripId))
                .willReturn(dto);

        // When & Then
        mvc.perform(get("/api/v1/trips/{id}", tripId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(tripId));
    }

    @Test
    void testGetTripById_whenTripDoesNotExist_shouldReturnNotFound() throws Exception {
        // Given
        String tripId = "123e4567-e89b-12d3-a456-426614174000";

        given(tripService.getTripById(tripId))
                .willThrow(new ResourceNotFoundException("Trip with id " + tripId + " not found"));

        // When & Then
        mvc.perform(get("/api/v1/trips/{id}", tripId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetTripById_whenTripIdIsInvalid_shouldReturnBadRequestWithCorrectJsonBody() throws Exception {
        // Given
        String invalidTripId = "invalid-uuid";

        String responseBody = new ObjectMapper()
                .createObjectNode()
                .set("id", new ObjectMapper().createArrayNode()
                        .add("Invalid UUID string: \"invalid-uuid\""))
                .toString();

        given(tripService.getTripById(invalidTripId))
                .willThrow(new InvalidArgumentException("Invalid UUID string: \"invalid-uuid\"", "id"));

        // When & Then
        mvc.perform(get("/api/v1/trips/{id}", invalidTripId))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testCreateFlightTrip_whenRequestIsValid_shouldReturnCreatedTrip() throws Exception {

        FlightTripRequestDTO request = FlightTripRequestDtoTestBuilder
                .validFlightTrip();

        TripResponseDTO response = TripResponseDTO
                .builder()
                .availableWeight(request.getAvailableWeight())
                .travelerId(request.getTravelerId())
                .build();

        given(tripService.createTrip(any(TripRequestDTO.class)))
                .willReturn(response);

        mvc.perform(post("/api/v1/trips/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.traveler_id").value(request.getTravelerId().toString()))
                .andExpect(jsonPath("$.busDetails").doesNotExist()); // Should be excluded by JsonInclude.NON_NULL

        verify(tripService).createTrip(any(TripRequestDTO.class));
    }

    @Test
    void testUpdateFlightTrip_whenTripExists_shouldReturnUpdatedTrip () throws Exception {
        // Given
        String tripId = "123e4567-e89b-12d3-a456-426614174000";
        FlightTripRequestDTO requestDTO = FlightTripRequestDTO
                .builder()
                .build();

        TripResponseDTO responseDTO = TripResponseDTO
                .builder()
                .id(UUID.fromString(tripId))
                .build();

        given(tripService.updateTrip(eq(tripId), any(FlightTripRequestDTO.class)))
                .willReturn(responseDTO);

        // When & Then
        // Use MockMvc to simulate PUT /api/v1/trips/flight/{id} call
        mvc.perform(put("/api/v1/trips/flights/{id}", tripId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tripId));
    }
    }
