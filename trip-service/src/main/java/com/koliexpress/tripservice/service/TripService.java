package com.koliexpress.tripservice.service;

import com.koliexpress.tripservice.dto.trip.*;

import java.util.List;

public interface TripService {
    List<TripResponseDtoNonVerbose> getAllTrips();

    TripResponseDto getTripById(String id);

    TripResponseDto createTrip(TripRequestDto trip);

    TripResponseDto updateTrip(String id, TripRequestDto request);

    void deleteTrip(String id);
}
