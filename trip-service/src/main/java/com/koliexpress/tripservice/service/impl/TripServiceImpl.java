package com.koliexpress.tripservice.service.impl;

import com.koliexpress.tripservice.dto.transport.BusTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.CarTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.mapper.TripMapper;
import com.koliexpress.tripservice.mapper.transport.BusTransportMapper;
import com.koliexpress.tripservice.mapper.transport.CarTransportMapper;
import com.koliexpress.tripservice.mapper.transport.FlightTransportMapper;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.transport.BusTransport;
import com.koliexpress.tripservice.model.transport.CarTransport;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import com.koliexpress.tripservice.repository.TravelerRepository;
import com.koliexpress.tripservice.repository.TripRepository;
import com.koliexpress.tripservice.service.TripService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TravelerRepository travelerRepository;

    private final TripMapper tripMapper;
    private final FlightTransportMapper flightTransportMapper;
    private final BusTransportMapper busTransportMapper;
    private final CarTransportMapper carTransportMapper;

    public TripServiceImpl(
            TripRepository tripRepository,
            TravelerRepository travelerRepository,
            TripMapper tripMapper,
            FlightTransportMapper flightTransportMapper,
            BusTransportMapper busTransportMapper,
            CarTransportMapper carTransportMapper
    ) {
        this.tripRepository = tripRepository;
        this.travelerRepository = travelerRepository;
        this.tripMapper = tripMapper;
        this.flightTransportMapper = flightTransportMapper;
        this.busTransportMapper = busTransportMapper;
        this.carTransportMapper = carTransportMapper;
    }

    @Override
    public List<TripResponseDTO> getAllTrips(){
        return tripRepository
                .findAll().stream()
                .map(tripMapper::toResponseDTO)
                .toList();
    }

    @Override
    public TripResponseDTO getTripById(String id){
        UUID tripId = UUID.fromString(id);
        Trip repositoryTrip = tripRepository
            .findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip with id " + id + " not found"));
        return tripMapper.toResponseDTO(repositoryTrip);
    }

    @Override
    public TripResponseDTO createFlightTrip(FlightTripRequestDTO request){
        // 1. Find the traveler
        Traveler traveler = travelerRepository
            .findById(request.getTravelerId())
            .orElseThrow(() -> new RuntimeException("Traveler with id " + request.getTravelerId() + " not found"));

        // 2. Map the request DTO to Trip entity
        Trip trip = tripMapper.toTrip(request);

        // 3. Add the missing fields
        trip.setTraveler(traveler);

        FlightTransportRequestDTO flightDetails = request.getFlightDetails();
        FlightTransport flightTransport = flightTransportMapper.toEntity(flightDetails);
        trip.setTransport(flightTransport);

        // 4. Save the trip
        Trip savedTrip = tripRepository.save(trip);

        return tripMapper.toResponseDTO(savedTrip);
    }

    @Override
    public TripResponseDTO createBusTrip(BusTripRequestDTO request){
        // 1. Find the traveler
        Traveler traveler = travelerRepository.findById(request.getTravelerId()).orElseThrow();

        // 2. Map the request DTO to Trip entity
        Trip trip = tripMapper.toTrip(request);

        // 3. Add the missing fields
        trip.setTraveler(traveler);

        BusTransportRequestDTO busDetails = request.getBusDetails();
        BusTransport busTransport = busTransportMapper.toEntity(busDetails);
        trip.setTransport(busTransport);

        // 4. Save the trip
        Trip savedTrip = tripRepository.save(trip);

        return tripMapper.toResponseDTO(savedTrip);
    }

    @Override
    public TripResponseDTO createCarTrip(CarTripRequestDTO request){
        // 1. Find the traveler
        Traveler traveler = travelerRepository.findById(request.getTravelerId()).orElseThrow();

        // 2. Map the request DTO to Trip entity
        Trip trip = tripMapper.toTrip(request);

        // 3. Add the missing fields
        trip.setTraveler(traveler);

        CarTransportRequestDTO carDetails = request.getCarDetails();
        CarTransport carTransport = carTransportMapper.toEntity(carDetails);
        trip.setTransport(carTransport);

        // 4. Save the trip
        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toResponseDTO(savedTrip);
    }

    @Override
    public TripResponseDTO updateTrip(String id, TripRequestDTO request){
        return null;
    }

    public void deleteTrip(String id){
        tripRepository.deleteById(UUID.fromString(id));
    }


}
