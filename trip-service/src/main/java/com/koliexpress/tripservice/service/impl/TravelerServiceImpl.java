package com.koliexpress.tripservice.service.impl;

import com.koliexpress.tripservice.dto.TravelerDetailResponseDTO;
import com.koliexpress.tripservice.dto.TravelerRequestDTO;
import com.koliexpress.tripservice.dto.TravelerSummaryResponseDTO;
import com.koliexpress.tripservice.exceptions.InvalidArgumentException;
import com.koliexpress.tripservice.exceptions.ResourceNotFoundException;
import com.koliexpress.tripservice.mapper.transport.TravelerMapper;
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
    public List<TravelerSummaryResponseDTO> getAllTravelers() {
        return travelerRepository.findAll().stream()
                .map(travelerMapper::toSummaryResponseDTO)
                .toList();
    }

    @Override
    public TravelerDetailResponseDTO getTravelerById(String id) {
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
        return travelerMapper.toDetailResponseDTO(repositoryTraveler);
    }

    @Override
    @Transactional
    public TravelerDetailResponseDTO createTraveler(TravelerRequestDTO request) {
        Traveler travelerToSave = travelerMapper.toEntity(request);
        Traveler savedTraveler = travelerRepository.save(travelerToSave);
        return travelerMapper.toDetailResponseDTO(savedTraveler);
    }
}
