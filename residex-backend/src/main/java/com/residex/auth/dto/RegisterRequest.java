package com.residex.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @Size(min = 6,max = 100)
    private String password;

    @NotBlank
    private String registerNumber;

    @NotBlank
    private String phone;

    @NotBlank
    private String parentName;

    @NotBlank
    private String parentPhone;

    @NotBlank
    private String emergencyContact;

    @NotBlank
    private String gender;

    @NotBlank
    private String address;

    @NotNull
    @Min(1)
    @Max(4)
    private Integer yearOfStudy;

    @NotNull
    private Long departmentId;
}