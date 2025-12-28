package com.koliexpress.tripservice.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class TravelerRequestDTO implements Serializable {
    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    String email;

    @NotNull
    String phoneNumber;
}
