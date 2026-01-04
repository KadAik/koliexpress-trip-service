package com.koliexpress.tripservice.builder.dto;

import com.koliexpress.tripservice.dto.TravelerRequestDto;

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

    public TravelerRequestDto build() {
        TravelerRequestDto obj = new TravelerRequestDto();
        obj.setFirstName(firstName);
        obj.setLastName(lastName);
        obj.setEmail(email);
        obj.setPhoneNumber(phoneNumber);
        return obj;
    }

    // Convenience methods for test scenarios
    public static TravelerRequestDto validTraveler() {
        return aTravelerRequestDto().build();
    }

    public static TravelerRequestDto withInvalidEmail() {
        return aTravelerRequestDto()
                .withEmail("invalid-email")  // Invalid email format
                .build();
    }

    public static TravelerRequestDto withNullFirstName() {
        return aTravelerRequestDto()
                .withFirstName(null)
                .build();
    }

    public static TravelerRequestDto withNullLastName() {
        return aTravelerRequestDto()
                .withLastName(null)
                .build();
    }

    public static TravelerRequestDto withNullEmail() {
        return aTravelerRequestDto()
                .withEmail(null)
                .build();
    }

    public static TravelerRequestDto withNullPhoneNumber() {
        return aTravelerRequestDto()
                .withPhoneNumber(null)
                .build();
    }

    public static TravelerRequestDto withEmptyFirstName() {
        return aTravelerRequestDto()
                .withFirstName("")
                .build();
    }

    public static TravelerRequestDto withEmptyLastName() {
        return aTravelerRequestDto()
                .withLastName("")
                .build();
    }

    public static TravelerRequestDto withEmptyPhoneNumber() {
        return aTravelerRequestDto()
                .withPhoneNumber("")
                .build();
    }

    // Example real-world travelers
    public static TravelerRequestDto janeSmith() {
        return aTravelerRequestDto()
                .withFirstName("Jane")
                .withLastName("Smith")
                .withEmail("jane.smith@company.com")
                .withPhoneNumber("+441234567890")
                .build();
    }

    public static TravelerRequestDto bobJohnson() {
        return aTravelerRequestDto()
                .withFirstName("Bob")
                .withLastName("Johnson")
                .withEmail("bob.johnson@traveler.net")
                .withPhoneNumber("+33123456789")
                .build();
    }

    public static TravelerRequestDto internationalTraveler() {
        return aTravelerRequestDto()
                .withFirstName("Maria")
                .withLastName("Garcia")
                .withEmail("maria.garcia@email.es")
                .withPhoneNumber("+34123456789")
                .build();
    }

    // Edge case scenarios
    public static TravelerRequestDto withSpecialCharactersInName() {
        return aTravelerRequestDto()
                .withFirstName("Jean-Pierre")
                .withLastName("O'Connor")
                .withEmail("jean-pierre.oconnor@example.com")
                .withPhoneNumber("+33123456789")
                .build();
    }

    public static TravelerRequestDto withLongPhoneNumber() {
        return aTravelerRequestDto()
                .withFirstName("Alex")
                .withLastName("Chen")
                .withEmail("alex.chen@example.com")
                .withPhoneNumber("+8612345678901")  // Chinese format
                .build();
    }

    public static TravelerRequestDto withCorporateEmail() {
        return aTravelerRequestDto()
                .withFirstName("Michael")
                .withLastName("Williams")
                .withEmail("michael.williams@corporate-company.co.uk")
                .withPhoneNumber("+441234567890")
                .build();
    }
}