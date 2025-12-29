package com.koliexpress.tripservice.service;

import com.koliexpress.tripservice.dto.trip.*;

import java.util.List;

public interface TripService {
    List<TripResponseDTO> getAllTrips();

    TripResponseDTO getTripById(String id);

    TripResponseDTO createFlightTrip(FlightTripRequestDTO trip);

    TripResponseDTO createBusTrip(BusTripRequestDTO trip);

    TripResponseDTO createCarTrip(CarTripRequestDTO trip);

    <R extends TripRequestDTO> TripResponseDTO updateTrip(String id, R request);

    void deleteTrip(String id);
}
