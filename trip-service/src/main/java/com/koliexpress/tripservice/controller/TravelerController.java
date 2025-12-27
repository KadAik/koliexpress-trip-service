package com.koliexpress.tripservice.controller;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDTO;
import com.koliexpress.tripservice.dto.TravelerRequestDTO;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDTO;
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
    public List<TravelerSummaryResponseDTO> getAllTravelers(){
        return travelerService.getAllTravelers();
    }

    @GetMapping("/{id}")
    public TravelerDetailResponseDTO
    getTravelerById(@PathVariable String id){
        return travelerService.getTravelerById(id);
    }

    @PostMapping
    public TravelerDetailResponseDTO createTraveler(@RequestBody TravelerRequestDTO traveler){
        return travelerService.createTraveler(traveler);
    }
}
