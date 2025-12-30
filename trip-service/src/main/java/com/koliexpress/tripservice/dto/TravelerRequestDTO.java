package com.koliexpress.tripservice.dto;

import java.io.Serializable;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class TravelerRequestDTO implements Serializable {
    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    @Email
    String email;

    @NotNull
    String phoneNumber;
}
