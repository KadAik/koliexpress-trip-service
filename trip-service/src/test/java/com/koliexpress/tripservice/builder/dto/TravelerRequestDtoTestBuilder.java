package com.koliexpress.tripservice.builder.dto;

import com.koliexpress.tripservice.dto.TravelerRequestDTO;

public class TravelerRequestDtoTestBuilder {
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@example.com";
    private String phoneNumber = "+1234567890";

    private TravelerRequestDtoTestBuilder() {
    }

    public static TravelerRequestDtoTestBuilder aTravelerRequestDto() {
        return new TravelerRequestDtoTestBuilder();
    }

    public TravelerRequestDtoTestBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TravelerRequestDtoTestBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TravelerRequestDtoTestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TravelerRequestDtoTestBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public TravelerRequestDTO build() {
        TravelerRequestDTO obj = new TravelerRequestDTO();
        obj.setFirstName(firstName);
        obj.setLastName(lastName);
        obj.setEmail(email);
        obj.setPhoneNumber(phoneNumber);
        return obj;
    }

    // Convenience methods for test scenarios
    public static TravelerRequestDTO validTraveler() {
        return aTravelerRequestDto().build();
    }

    public static TravelerRequestDTO withInvalidEmail() {
        return aTravelerRequestDto()
                .withEmail("invalid-email")  // Invalid email format
                .build();
    }

    public static TravelerRequestDTO withNullFirstName() {
        return aTravelerRequestDto()
                .withFirstName(null)
                .build();
    }

    public static TravelerRequestDTO withNullLastName() {
        return aTravelerRequestDto()
                .withLastName(null)
                .build();
    }

    public static TravelerRequestDTO withNullEmail() {
        return aTravelerRequestDto()
                .withEmail(null)
                .build();
    }

    public static TravelerRequestDTO withNullPhoneNumber() {
        return aTravelerRequestDto()
                .withPhoneNumber(null)
                .build();
    }

    public static TravelerRequestDTO withEmptyFirstName() {
        return aTravelerRequestDto()
                .withFirstName("")
                .build();
    }

    public static TravelerRequestDTO withEmptyLastName() {
        return aTravelerRequestDto()
                .withLastName("")
                .build();
    }

    public static TravelerRequestDTO withEmptyPhoneNumber() {
        return aTravelerRequestDto()
                .withPhoneNumber("")
                .build();
    }

    // Example real-world travelers
    public static TravelerRequestDTO janeSmith() {
        return aTravelerRequestDto()
                .withFirstName("Jane")
                .withLastName("Smith")
                .withEmail("jane.smith@company.com")
                .withPhoneNumber("+441234567890")
                .build();
    }

    public static TravelerRequestDTO bobJohnson() {
        return aTravelerRequestDto()
                .withFirstName("Bob")
                .withLastName("Johnson")
                .withEmail("bob.johnson@traveler.net")
                .withPhoneNumber("+33123456789")
                .build();
    }

    public static TravelerRequestDTO internationalTraveler() {
        return aTravelerRequestDto()
                .withFirstName("Maria")
                .withLastName("Garcia")
                .withEmail("maria.garcia@email.es")
                .withPhoneNumber("+34123456789")
                .build();
    }

    // Edge case scenarios
    public static TravelerRequestDTO withSpecialCharactersInName() {
        return aTravelerRequestDto()
                .withFirstName("Jean-Pierre")
                .withLastName("O'Connor")
                .withEmail("jean-pierre.oconnor@example.com")
                .withPhoneNumber("+33123456789")
                .build();
    }

    public static TravelerRequestDTO withLongPhoneNumber() {
        return aTravelerRequestDto()
                .withFirstName("Alex")
                .withLastName("Chen")
                .withEmail("alex.chen@example.com")
                .withPhoneNumber("+8612345678901")  // Chinese format
                .build();
    }

    public static TravelerRequestDTO withCorporateEmail() {
        return aTravelerRequestDto()
                .withFirstName("Michael")
                .withLastName("Williams")
                .withEmail("michael.williams@corporate-company.co.uk")
                .withPhoneNumber("+441234567890")
                .build();
    }
}