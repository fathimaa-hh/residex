package com.residex.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String name;

    private String email;

    private String password;

    private String registerNumber;

    private String phone;

    private String gender;

    private String address;

    private String parentName;

    private String parentPhone;

    private String emergencyContact;

    private Integer yearOfStudy;

    private Long departmentId;
}