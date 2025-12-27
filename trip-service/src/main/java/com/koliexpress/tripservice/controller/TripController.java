package com.koliexpress.tripservice.controller;

import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.service.TripService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<TripResponseDTO> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public TripResponseDTO getTripById(@PathVariable String id){
        return tripService.getTripById(id);
    }

    @PostMapping("/flights")
    public TripResponseDTO createFlightTrip(@RequestBody FlightTripRequestDTO trip){
        return tripService.createFlightTrip(trip);
    }

    @PostMapping("/buses")
    public TripResponseDTO createBusTrip(@RequestBody BusTripRequestDTO trip){
        return tripService.createBusTrip(trip);
    }

    @PostMapping("/cars")
    public TripResponseDTO createCarTrip(@RequestBody CarTripRequestDTO trip){
        return tripService.createCarTrip(trip);
    }

    @PutMapping("/{id}")
    public TripResponseDTO updateTrip(@PathVariable String id, @RequestBody TripRequestDTO trip){
        return tripService.updateTrip(id, trip);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable String id){
        tripService.deleteTrip(id);
    }
}
