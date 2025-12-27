package com.koliexpress.tripservice.service;

import java.util.List;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDTO;
import com.koliexpress.tripservice.dto.TravelerRequestDTO;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDTO;

public interface TravelerService {
    List<TravelerSummaryResponseDTO> getAllTravelers();
    TravelerDetailResponseDTO getTravelerById(String id);
    TravelerDetailResponseDTO createTraveler(TravelerRequestDTO traveler);
}
