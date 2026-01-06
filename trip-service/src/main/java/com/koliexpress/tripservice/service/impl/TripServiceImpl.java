package com.koliexpress.tripservice.service.impl;

import com.koliexpress.tripservice.dto.LocationRequestDto;
import com.koliexpress.tripservice.dto.transport.FlightTransportResponseDto;
import com.koliexpress.tripservice.dto.transport.TransportRequestDto;
import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.mapper.LocationMapper;
import com.koliexpress.tripservice.mapper.TripMapper;
import com.koliexpress.tripservice.mapper.transport.TransportMapper;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.model.Trip;
import com.koliexpress.tripservice.model.transport.FlightTransport;
import com.koliexpress.tripservice.model.transport.Transport;
import com.koliexpress.tripservice.repository.TravelerRepository;
import com.koliexpress.tripservice.repository.TripRepository;
import com.koliexpress.tripservice.service.TripService;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TravelerRepository travelerRepository;

    private final TripMapper tripMapper;
    private final LocationMapper locationMapper;
    private final TransportMapper transportMapper;

    public TripServiceImpl(
            TripRepository tripRepository,
            TravelerRepository travelerRepository,
            TripMapper tripMapper,
            LocationMapper locationMapper, TransportMapper transportMapper) {
        this.tripRepository = tripRepository;
        this.travelerRepository = travelerRepository;
        this.tripMapper = tripMapper;
        this.locationMapper = locationMapper;
        this.transportMapper = transportMapper;
    }

    @Override
    public List<TripResponseDtoNonVerbose> getAllTrips(){
        return tripRepository
                .findAll().stream()
                .map(tripMapper::toDtoNonVerbose)
                .toList();
    }

    @Override
    @Transactional
    public TripResponseDto getTripById(String id){
        if (id == null || id.isEmpty()) {
            throw new InvalidArgumentException("ID is required", "id");
        }
        UUID tripId = parseUUID(id);

        Trip repositoryTrip = tripRepository
            .findById(tripId)
            .orElseThrow(() -> new ResourceNotFoundException("Trip with id " + id + " not found"));

        Transport transport = repositoryTrip.getTransport();
        // Transport might be lazy loaded and might be a Hibernate proxy so we initialize it.
        if (transport instanceof HibernateProxy) {
            Hibernate.initialize(transport);  // Force load from DB
            Transport unproxied = (Transport) Hibernate.unproxy(transport);  // Get real object
            repositoryTrip.setTransport(unproxied);  // Replace proxy with real object in Trip
        }

        return tripMapper.toDto(repositoryTrip);
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

        return tripMapper.toDto(savedTrip);
    }

    @Override
    @Transactional
    public TripResponseDto updateTrip(String id, TripRequestDto dto){
        // 1. Retrieve the Trip to be updated
        Trip repositoryTrip = tripRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException("Trip with id " + id + " not found"));

        // 2. Handle transport update
        TransportRequestDto transportFromDto = dto.getTransport();
        if(transportFromDto != null) {
            // Transport type is not updatable, create a new Trip if needed
            // But we do need the type in the request to instantiate the correct Transport subclass
            if (transportFromDto.getType() != repositoryTrip.getTransportType()) {
                throw new InvalidArgumentException(
                        "Transport type cannot be changed. Create a new Trip with the desired transport type.",
                        "transport.type"
                );
            }
            Transport oldTransport = repositoryTrip.getTransport();
            // Related entities such as Transport may have been lazy loaded (Hibernate proxies)
            Hibernate.initialize(oldTransport);  // Force load from DB
            Transport unproxied = (Transport) Hibernate.unproxy(oldTransport);  // Get the real object
            Transport updatedTransport = transportMapper.updateEntityFromDtoAndReturn(transportFromDto, unproxied);
            repositoryTrip.setTransport(updatedTransport);
        }

        // 3. Handle origin and destination (locations)
        LocationRequestDto originFromDto = dto.getOrigin();
        LocationRequestDto destinationFromDto = dto.getDestination();
        // Locations are value objects -> immutable
        if(originFromDto != null){
            repositoryTrip.setOrigin(locationMapper.toEntity(originFromDto));
        }
        if(destinationFromDto != null){
            repositoryTrip.setDestination(locationMapper.toEntity(destinationFromDto));
        }

        // 4. Finalize
        // Mapper ignores locations and transport
        Trip updatedTrip = tripMapper.updateEntityFromDtoAndReturn(dto, repositoryTrip);

        // 5. Set the updated date
        updatedTrip.setUpdatedAt(LocalDateTime.now());

        // 6. Save the updated Trip : due to @Transactional, this is optional but for clarity
        Trip savedTrip = tripRepository.save(updatedTrip);

        return tripMapper.toDto(savedTrip);
    }

    public void deleteTrip(String id){
        tripRepository.deleteById(UUID.fromString(id));
    }

    private static UUID parseUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            throw new InvalidArgumentException("Invalid UUID string", "id");
        }
    }


}
