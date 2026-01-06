package com.koliexpress.tripservice.model;

import com.koliexpress.tripservice.config.ApiPaths;
import com.koliexpress.tripservice.enums.TransportType;
import com.koliexpress.tripservice.enums.TripStatus;
import com.koliexpress.tripservice.model.transport.Transport;
import com.koliexpress.tripservice.valueobjects.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Trip entity - Generic model for all types of transport
 * Uses Strategy pattern with Transport for specific transport types
 */
@Entity
@Table(name = "trips")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traveler_id", nullable = false)
    private Traveler traveler;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "origin_name")),
            @AttributeOverride(name = "city", column = @Column(name = "origin_city")),
            @AttributeOverride(name = "country", column = @Column(name = "origin_country")),
            @AttributeOverride(name = "latitude", column = @Column(name = "origin_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "origin_longitude"))
    })
    private Location origin;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "destination_name")),
            @AttributeOverride(name = "city", column = @Column(name = "destination_city")),
            @AttributeOverride(name = "country", column = @Column(name = "destination_country")),
            @AttributeOverride(name = "latitude", column = @Column(name = "destination_latitude")),
            @AttributeOverride(name = "longitude", column = @Column(name = "destination_longitude"))
    })
    private Location destination;

    @NotNull
    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @NotNull
    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @NotNull
    @Column(name = "available_weight", nullable = false)
    private BigDecimal availableWeight;

    @NotNull
    @Column(name = "price_per_kg", nullable = false)
    private BigDecimal pricePerKg;

    @Column(name = "price_asked", nullable = false)
    private BigDecimal priceAsked;

    // Transport type (FLIGHT, BUS, CAR, etc.)
    @NotNull(message = "Transport type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "transport_type", nullable = false)
    private TransportType transportType;

    // Details specific to the transport (polymorphic) : FlightTransport, BusTransport, etc.
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TripStatus status = TripStatus.DRAFT;

    @Column(name = "notice", columnDefinition = "TEXT")
    private String notice;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Business methods
    public boolean isFlight() {
        return TransportType.PLANE.equals(transportType);
    }

    public boolean isBus() {
        return TransportType.BUS.equals(transportType);
    }

    public boolean isCar() {
        return TransportType.CAR.equals(transportType);
    }

    public boolean isActive() {
        return switch (status) {
            case AVAILABLE, MATCHED, IN_PROGRESS -> true;
            default -> false;
        };
    }

    @Transient
    public String getUrl(){
        return ApiPaths.TRIPS.path()
                + '/'
                + this.getId();
    }

}
