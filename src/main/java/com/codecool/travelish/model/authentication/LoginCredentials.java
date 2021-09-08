package com.codecool.travelish.model.authentication;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class LoginCredentials {
    private String email;
    private String password;
}
