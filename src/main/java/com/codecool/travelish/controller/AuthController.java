package com.codecool.travelish.controller;

import com.codecool.travelish.model.authentication.LoginRequestDto;
import com.codecool.travelish.model.authentication.LoginResponseDto;
import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.security.JwtTokenService;
import com.codecool.travelish.service.AppUserService;
import com.codecool.travelish.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final AppUserService appUserService;
    private final CompanyService companyService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, AppUserService appUserService, CompanyService companyService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.appUserService = appUserService;
        this.companyService = companyService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginRequestDto data) {
        try {
            String email = data.getEmail();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, data.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenService.createToken(email, roles);

            LoginResponseDto loginResponseDto = getAccountType(email, token, roles);
            return ResponseEntity.ok(loginResponseDto);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody @Valid AppUser appUser) {
        if (appUserService.existsByEmail(appUser.getEmail())) {
            return ResponseEntity.badRequest().body("An account with this email already exists.");
        }
        // # Todo create app user object
        appUserService.save(appUser);
        return ResponseEntity.ok("User has been registered successfully.");
    }

    @PostMapping("/register-company")
    public ResponseEntity<?> registerCompany(@RequestBody @Valid Company company) {
        if (companyService.existsByEmail(company.getEmail())) {
            return ResponseEntity.badRequest().body("An account with this email already exists.");
        }
        // # Todo create app user object
        companyService.save(company);
        return ResponseEntity.ok("Company has been registered successfully.");
    }

    public LoginResponseDto getAccountType(String email, String token, List<String> roles) {
        LoginResponseDto loginResponseDto;
        if (appUserService.existsByEmail(email)) {
            loginResponseDto = new LoginResponseDto(appUserService.findByEmail(email).getId(), roles, token, email);
        } else {
            loginResponseDto = new LoginResponseDto(companyService.findByEmail(email).getId(), roles, token, email);
        }
        return loginResponseDto;
    }
}
