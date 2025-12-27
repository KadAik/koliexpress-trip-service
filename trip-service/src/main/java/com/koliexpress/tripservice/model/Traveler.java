package com.koliexpress.tripservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "travelers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "traveler")
    private List<Trip> trips = new ArrayList<>();
}
