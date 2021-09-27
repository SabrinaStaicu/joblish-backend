package com.codecool.travelish.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private Long id;
    private List<String> roles;
    private String token;
    private String email;
}
