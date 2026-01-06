package com.koliexpress.tripservice.repository;


import com.koliexpress.tripservice.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {

    @Query("""
            SELECT t FROM Trip t
            LEFT JOIN FETCH t.transport tr
            WHERE t.id = :id
    """)
    Optional<Trip> findByIdWithTransport(UUID id);

}
