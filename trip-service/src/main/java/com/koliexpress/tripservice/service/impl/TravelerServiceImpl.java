package com.koliexpress.tripservice.service.impl;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDto;
import com.koliexpress.tripservice.dto.TravelerRequestDto;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDto;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.mapper.TravelerMapper;
import com.koliexpress.tripservice.model.Traveler;
import com.koliexpress.tripservice.repository.TravelerRepository;
import com.koliexpress.tripservice.service.TravelerService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TravelerServiceImpl implements TravelerService {

    private final TravelerRepository travelerRepository;
    private final TravelerMapper travelerMapper;

    public TravelerServiceImpl(TravelerRepository travelerRepository, TravelerMapper travelerMapper) {
        this.travelerRepository = travelerRepository;
        this.travelerMapper = travelerMapper;
    }

    @Override
    public List<TravelerSummaryResponseDto> getAllTravelers() {
        return travelerRepository.findAll().stream()
                .map(travelerMapper::toSummaryDto)
                .toList();
    }

    @Override
    public TravelerDetailResponseDto getTravelerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidArgumentException("ID is required", "id");
        }
        UUID travelerId;
        try {
            travelerId = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException("Invalid UUID format", "id");
        }
        Traveler repositoryTraveler = travelerRepository
                .findById(travelerId)
                .orElseThrow(
                    () -> new ResourceNotFoundException("Traveler with id " + id + " not found")
                );
        return travelerMapper.toDto(repositoryTraveler);
    }

    @Override
    @Transactional
    public TravelerDetailResponseDto createTraveler(TravelerRequestDto request) {
        Traveler travelerToSave = travelerMapper.toEntity(request);
        Traveler savedTraveler = travelerRepository.save(travelerToSave);
        return travelerMapper.toDto(savedTraveler);
    }
}
