package com.codecool.travelish.model.authentication;


import lombok.Data;

import java.util.List;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
