package com.koliexpress.tripservice.controller;

import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.service.TripService;
import com.koliexpress.tripservice.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of all trips",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TripResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public List<TripResponseDTO> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public TripResponseDTO getTripById(@PathVariable String id){
        return tripService.getTripById(id);
    }

    @PostMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponseDTO createFlightTrip(@Validated(ValidationGroups.Create.class) @RequestBody FlightTripRequestDTO trip){
        return tripService.createTrip(trip);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/buses")
    public TripResponseDTO createBusTrip(@Validated(ValidationGroups.Create.class) @RequestBody BusTripRequestDTO trip){
        return tripService.createTrip(trip);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cars")
    public TripResponseDTO createCarTrip(@Validated(ValidationGroups.Create.class) @RequestBody CarTripRequestDTO trip){
        return tripService.createTrip(trip);
    }

    @PutMapping("/flights/{id}")
    public TripResponseDTO updateFlightTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody FlightTripRequestDTO request){
        return tripService.updateTrip(id, request);
    }

    @PutMapping("/buses/{id}")
    public TripResponseDTO updateBusTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody BusTripRequestDTO request){
        return tripService.updateTrip(id, request);
    }

    @PutMapping("/cars/{id}")
    public TripResponseDTO updateCarTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody CarTripRequestDTO request){
        return tripService.updateTrip(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable String id){
        tripService.deleteTrip(id);
    }
}
