package com.koliexpress.tripservice.service;

import java.util.List;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDto;
import com.koliexpress.tripservice.dto.TravelerRequestDto;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDto;

public interface TravelerService {
    List<TravelerSummaryResponseDto> getAllTravelers();
    TravelerDetailResponseDto getTravelerById(String id);
    TravelerDetailResponseDto createTraveler(TravelerRequestDto traveler);
}
