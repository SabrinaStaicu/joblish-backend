package com.codecool.travelish.model.authentication;


import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
