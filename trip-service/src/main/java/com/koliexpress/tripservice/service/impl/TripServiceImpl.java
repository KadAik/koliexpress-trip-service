package com.koliexpress.tripservice.service.impl;

import com.koliexpress.tripservice.dto.transport.BusTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.CarTransportRequestDTO;
import com.koliexpress.tripservice.dto.transport.FlightTransportRequestDTO;
import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.mapper.LocationMapper;
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
import jakarta.transaction.Transactional;
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
    private final LocationMapper locationMapper;

    public TripServiceImpl(
            TripRepository tripRepository,
            TravelerRepository travelerRepository,
            TripMapper tripMapper,
            FlightTransportMapper flightTransportMapper,
            BusTransportMapper busTransportMapper,
            CarTransportMapper carTransportMapper,
            LocationMapper locationMapper) {
        this.tripRepository = tripRepository;
        this.travelerRepository = travelerRepository;
        this.tripMapper = tripMapper;
        this.flightTransportMapper = flightTransportMapper;
        this.busTransportMapper = busTransportMapper;
        this.carTransportMapper = carTransportMapper;
        this.locationMapper = locationMapper;
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
        if (id == null || id.isEmpty()) {
            throw new InvalidArgumentException("ID is required", "id");
        }
        UUID tripId;
        try {
            tripId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Invalid UUID format", "id");
        }

        Trip repositoryTrip = tripRepository
            .findById(tripId)
            .orElseThrow(() -> new ResourceNotFoundException("Trip with id " + id + " not found"));
        return tripMapper.toResponseDTO(repositoryTrip);
    }

    @Override
    @Transactional
    public TripResponseDTO createFlightTrip(FlightTripRequestDTO request){
        // 1. Find the traveler
        Traveler traveler = travelerRepository
            .findById(request.getTravelerId())
            .orElseThrow(() -> new ResourceNotFoundException("Traveler with id " + request.getTravelerId() + " not found"));

        // 2. Map the request DTO to Trip entity
        Trip trip = tripMapper.toEntity(request);

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
    @Transactional
    public TripResponseDTO createBusTrip(BusTripRequestDTO request){
        // 1. Find the traveler
        Traveler traveler = travelerRepository
        .findById(request.getTravelerId())
        .orElseThrow(
            () -> new ResourceNotFoundException("Traveler with id " + request.getTravelerId() + " not found")
        );

        // 2. Map the request DTO to Trip entity
        Trip trip = tripMapper.toEntity(request);

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
    @Transactional
    public TripResponseDTO createCarTrip(CarTripRequestDTO request){
        // 1. Find the traveler
        Traveler traveler = travelerRepository
            .findById(request.getTravelerId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Traveler with id " + request.getTravelerId() + " not found")
            );

        // 2. Map the request DTO to Trip entity
        Trip trip = tripMapper.toEntity(request);

        // 3. Add the missing fields
        trip.setTraveler(traveler);

        // 4. Save the trip
        Trip savedTrip = tripRepository.save(trip);
        return tripMapper.toResponseDTO(savedTrip);
    }

    @Override
    @Transactional
    public TripResponseDTO updateTrip(String id, TripRequestDTO request){
        // 1. Retrieve the Trip to be updated
        Trip repositoryTrip = tripRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException("Trip with id " + id + " not found"));

        // 2. Perform the update
        Trip updatedTrip = switch (request) {
            case FlightTripRequestDTO flightTripRequestDTO ->
                    tripMapper.updateEntityFromDtoAndReturn(flightTripRequestDTO, repositoryTrip, locationMapper);
            case BusTripRequestDTO busTripRequestDTO ->
                    tripMapper.updateEntityFromDtoAndReturn(busTripRequestDTO, repositoryTrip, locationMapper);
            case CarTripRequestDTO carTripRequestDTO ->
                    tripMapper.updateEntityFromDtoAndReturn(carTripRequestDTO, repositoryTrip, locationMapper);
            default ->
                    throw new InvalidArgumentException("Unsupported TripRequestDTO type : " + request.getClass().getName(), "request");
        };

        // 3. Save the updated Trip : due to @Transactional, this is optional but for clarity
        Trip savedTrip = tripRepository.save(updatedTrip);

        return tripMapper.toResponseDTO(savedTrip);
    }

    public void deleteTrip(String id){
        tripRepository.deleteById(UUID.fromString(id));
    }


}
