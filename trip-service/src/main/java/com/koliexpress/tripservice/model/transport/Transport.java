package com.koliexpress.tripservice.model.transport;

import com.koliexpress.tripservice.enums.TransportVerificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transports")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transport_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "provider_name")
    private String providerName; // (airline, bus company, etc.)

    @Column(name = "reference_number")
    private String referenceNumber; // Flight no, bus ticket, etc.

    @Column(name = "verification_status")
    @Enumerated(EnumType.STRING)
    private TransportVerificationStatus verificationStatus = TransportVerificationStatus.PENDING;

    @Column(name = "additional_info", columnDefinition = "TEXT")
    private String additionalInfo;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Hook method for specific validation
    public abstract boolean validate();

    // Hook method to obtain a summary
    public abstract String getSummary();
}

