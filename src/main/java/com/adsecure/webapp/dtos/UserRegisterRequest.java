package com.adsecure.webapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @NotBlank
    private String gender;

    @NotBlank
    private String phoneNo;

    @Email
    @NotBlank
    private String mail;

    @NotBlank
    private String password;
}
