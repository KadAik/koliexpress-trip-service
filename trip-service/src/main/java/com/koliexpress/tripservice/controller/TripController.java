package com.koliexpress.tripservice.controller;

import com.koliexpress.tripservice.dto.trip.*;
import com.koliexpress.tripservice.service.TripService;
import com.koliexpress.tripservice.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
                            schema = @Schema(implementation = TripResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    public List<TripResponseDto> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public TripResponseDto getTripById(@PathVariable String id){
        return tripService.getTripById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponseDto createTrip(@Validated(ValidationGroups.Create.class) @RequestBody TripRequestDto request){
        return tripService.createTrip(request);
    }

    @PutMapping("/flights/{id}")
    public TripResponseDto updateFlightTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody FlightTripRequestDto request){
        return tripService.updateTrip(id, request);
    }

    @PutMapping("/buses/{id}")
    public TripResponseDto updateBusTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody BusTripRequestDto request){
        return tripService.updateTrip(id, request);
    }

    @PutMapping("/cars/{id}")
    public TripResponseDto updateCarTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody CarTripRequestDto request){
        return tripService.updateTrip(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable String id){
        tripService.deleteTrip(id);
    }
}
