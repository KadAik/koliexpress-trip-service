package com.koliexpress.tripservice.controller;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDto;
import com.koliexpress.tripservice.dto.TravelerRequestDto;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDto;
import com.koliexpress.tripservice.service.TravelerService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/travelers")
public class TravelerController {

    private final TravelerService travelerService;

    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }


    @GetMapping
    public List<TravelerSummaryResponseDto> getAllTravelers(){
        return travelerService.getAllTravelers();
    }

    @GetMapping("/{id}")
    public TravelerDetailResponseDto
    getTravelerById(@PathVariable String id){
        return travelerService.getTravelerById(id);
    }

    @PostMapping
    public TravelerDetailResponseDto createTraveler(@RequestBody TravelerRequestDto traveler){
        return travelerService.createTraveler(traveler);
    }
}
