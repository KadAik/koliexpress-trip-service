package com.koliexpress.tripservice.dto;

import com.koliexpress.tripservice.validation.ValidationGroups;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelerRequestDTO implements Serializable {

    @NotBlank(
            message = "First name is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 100, message = "First name cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, }
    )
    String firstName;

    @NotBlank(
            message = "Last name is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 100, message = "Last name cannot exceed 100 characters",
            groups = {ValidationGroups.Create.class, }
    )
    String lastName;

    @NotBlank(
            message = "Email is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Email(
            message = "Email must be valid",
            groups = {ValidationGroups.Create.class, }
    )
    @Size(
            max = 255, message = "Email cannot exceed 255 characters",
            groups = {ValidationGroups.Create.class, }
    )
    String email;

    @NotBlank(
            message = "Phone number is required",
            groups = {ValidationGroups.Create.class, }
    )
    @Pattern(
            regexp = "^\\+?[0-9\\s\\-()]{7,20}$",
            message = "Phone number must be valid (7-20 digits, optional +)",
            groups = {ValidationGroups.Create.class, }
    )
    String phoneNumber;
}