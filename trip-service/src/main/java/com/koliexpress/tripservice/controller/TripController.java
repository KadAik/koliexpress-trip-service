package com.koliexpress.tripservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    public TripController(TripService tripService, ObjectMapper objectMapper) {
        this.tripService = tripService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<TripResponseDtoNonVerbose> getAllTrips(){
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TripResponseDto updateTrip(@PathVariable String id, @Validated(ValidationGroups.Update.class) @RequestBody TripRequestDto request) throws Exception{
        System.out.println("Payload : " + objectMapper.writeValueAsString(request));
        return tripService.updateTrip(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable String id){
        tripService.deleteTrip(id);
    }
}
