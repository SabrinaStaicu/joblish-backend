package com.codecool.travelish.model.authentication;

import com.codecool.travelish.model.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private Long id;
    private List<String> roles;
    private String token;
    private String email;
}
