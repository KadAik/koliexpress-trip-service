package com.koliexpress.tripservice.repository;


import com.koliexpress.tripservice.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

}
