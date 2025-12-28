package com.koliexpress.tripservice.controller;


import com.koliexpress.tripservice.dto.trip.TripResponseDTO;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.service.TripService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    TripService tripService;

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
}
