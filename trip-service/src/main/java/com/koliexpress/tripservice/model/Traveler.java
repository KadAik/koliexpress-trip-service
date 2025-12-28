package com.koliexpress.tripservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column( name = "first_name", nullable = false, length = 50 )
    @Min(value = 3, message = "First name must be at least 3 characters long")
    private String firstName;

    @NotNull
    @Column( name = "last_name", nullable = false, length = 50 )
    @Min(value = 3, message = "Last name must be at least 3 characters long")
    private String lastName;

    @NotNull
    @Column( name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @NotNull
    @Column( name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "traveler")
    private List<Trip> trips = new ArrayList<>();
}
