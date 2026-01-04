package com.koliexpress.tripservice.service.impl;

import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDto;
import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.mapper.LocationMapper;
import com.koliexpress.tripservice.mapper.TripMapper;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import com.koliexpress.tripservice.model.transport.Transport;
import com.koliexpress.tripservice.repository.TravelerRepository;
import com.koliexpress.tripservice.repository.TripRepository;
import com.koliexpress.tripservice.service.TripService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TravelerRepository travelerRepository;

    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;

    public TripServiceImpl(
            TripRepository tripRepository,
            TravelerRepository travelerRepository,
            TripMapper tripMapper,
            LocationMapper locationMapper) {
        this.tripRepository = tripRepository;
        this.travelerRepository = travelerRepository;
        this.tripMapper = tripMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public List<TripResponseDto> getAllTrips(){
        return tripRepository
                .findAll().stream()
                .map(tripMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public TripResponseDto getTripById(String id){
        if (id == null || id.isEmpty()) {
            throw new InvalidArgumentException("ID is required", "id");
        }
        UUID tripId;
        try {
            tripId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Invalid UUID string", "id");
        }

        Trip repositoryTrip = tripRepository
            .findById(tripId)
            .orElseThrow(() -> new ResourceNotFoundException("Trip with id " + id + " not found"));

        Transport transport = repositoryTrip.getTransport();

        // LOG 1: Check what we got from database
        System.out.println("=== BEFORE INITIALIZE ===");
        System.out.println("Transport class: " + transport.getClass().getName());
        System.out.println("Is Transport: " + (transport instanceof Transport));
        System.out.println("Is HibernateProxy: " + (transport instanceof org.hibernate.proxy.HibernateProxy));
        System.out.println("Is FlightTransport: " + (transport instanceof FlightTransport));
        System.out.println("IsFlightTransport: " + (transport.isFlight()));

        // UNWRAP THE PROXY
        if (transport != null) {
            Hibernate.initialize(transport);  // Force load from DB
            Transport unproxied = (Transport) Hibernate.unproxy(transport);  // Get real object
            repositoryTrip.setTransport(unproxied);  // Replace proxy with real object in Trip
        }

        // AFTER
        System.out.println("=== AFTER UNPROXY ===");
        System.out.println("Transport class: " + repositoryTrip.getTransport().getClass().getName());
        System.out.println("Is HibernateProxy: " + (repositoryTrip.getTransport() instanceof org.hibernate.proxy.HibernateProxy));
        System.out.println("Is FlightTransport: " + (repositoryTrip.getTransport() instanceof FlightTransport));

        TripResponseDto response = tripMapper.toResponseDto(repositoryTrip);
        // Check result
        System.out.println("=== RESULT ===");
        System.out.println("Dto class: " + response.getTransportDetails().getClass().getName());
        if (response.getTransportDetails() instanceof FlightTransportResponseDto flight) {
            System.out.println("✓ It's a FlightTransportResponseDto!");
            System.out.println("Flight number: " + flight.getFlightNumber());
        } else {
            System.out.println("✗ It's just a base TransportResponseDto");
        }

    return response;
    }

    @Override
    @Transactional
    public TripResponseDto createTrip(TripRequestDto request) {

        // 1. Find traveler
        Traveler traveler = travelerRepository
                .findById(request.getTravelerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Traveler with id " + request.getTravelerId() + " not found"
                        )
                );

        // 2. Create Trip entity
        Trip trip = tripMapper.toEntity(request); // mapper handles polymorphism

        // 3. Set non-mapped fields
        trip.setTraveler(traveler);
        trip.setTransportType(trip.getTransport().getType());

        // 4. Persist
        Trip savedTrip = tripRepository.save(trip);

        return tripMapper.toResponseDto(savedTrip);
    }

    @Override
    @Transactional
    public TripResponseDto updateTrip(String id, TripRequestDto request){
        // 1. Retrieve the Trip to be updated
        Trip repositoryTrip = tripRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException("Trip with id " + id + " not found"));

        // 2. Perform the update
        Trip updatedTrip = switch (request) {
            case FlightTripRequestDto flightTripRequestDto ->
                    tripMapper.updateEntityFromDtoAndReturn(flightTripRequestDto, repositoryTrip, locationMapper);
            case BusTripRequestDto busTripRequestDto ->
                    tripMapper.updateEntityFromDtoAndReturn(busTripRequestDto, repositoryTrip, locationMapper);
            case CarTripRequestDto carTripRequestDto ->
                    tripMapper.updateEntityFromDtoAndReturn(carTripRequestDto, repositoryTrip, locationMapper);
            default ->
                    throw new InvalidArgumentException("Unsupported TripRequestDto type : " + request.getClass().getName(), "request");
        };

        // 3. Save the updated Trip : due to @Transactional, this is optional but for clarity
        Trip savedTrip = tripRepository.save(updatedTrip);

        return tripMapper.toResponseDto(savedTrip);
    }

    public void deleteTrip(String id){
        tripRepository.deleteById(UUID.fromString(id));
    }


}
