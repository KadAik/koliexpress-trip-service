package com.koliexpress.tripservice.repository;

import com.koliexpress.tripservice.model.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TravelerRepository extends JpaRepository<Traveler, UUID> {
}