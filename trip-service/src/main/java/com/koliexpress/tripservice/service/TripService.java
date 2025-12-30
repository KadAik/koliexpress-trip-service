package com.koliexpress.tripservice.service;

import com.koliexpress.tripservice.dto.trip.*;

import java.util.List;

public interface TripService {
    List<TripResponseDTO> getAllTrips();

    TripResponseDTO getTripById(String id);

    TripResponseDTO createTrip(TripRequestDTO trip);

    TripResponseDTO updateTrip(String id, TripRequestDTO request);

    void deleteTrip(String id);
}
