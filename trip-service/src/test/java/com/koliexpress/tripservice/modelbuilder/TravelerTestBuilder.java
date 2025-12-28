package com.koliexpress.tripservice.modelbuilder;

import com.koliexpress.tripservice.model.Traveler;
import java.util.UUID;

public class TravelerTestBuilder {

    private UUID id = UUID.randomUUID();
    private String firstName = "John";
    private String lastName = "Doe";
    private String email = "john.doe@example.com";
    private String phoneNumber = "+22997123456";

    private TravelerTestBuilder() {}

    public static TravelerTestBuilder aDefaultTraveler() {
        return new TravelerTestBuilder();
    }

    public static TravelerTestBuilder aTraveler() {
        return new TravelerTestBuilder();
    }

    public TravelerTestBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public TravelerTestBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public TravelerTestBuilder withName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        return this;
    }

    public TravelerTestBuilder withoutId() {
        this.id = null;
        return this;
    }

    public Traveler build() {
        Traveler traveler = new Traveler();
        traveler.setId(id);
        traveler.setFirstName(firstName);
        traveler.setLastName(lastName);
        traveler.setEmail(email);
        traveler.setPhoneNumber(phoneNumber);
        return traveler;
    }
}
