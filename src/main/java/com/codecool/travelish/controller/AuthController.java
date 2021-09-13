package com.codecool.travelish.controller;

import com.codecool.travelish.model.authentication.LoginCredentials;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.security.JwtTokenService;
import com.codecool.travelish.service.AppUserService;
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

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, AppUserService appUserService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.appUserService = appUserService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody LoginCredentials data) {
        try {
            String username = data.getEmail();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, data.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenService.createToken(username, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> register(@RequestBody @Valid AppUser appUser) {
        if (appUserService.existsByEmail(appUser.getEmail())) {
            return ResponseEntity.badRequest().body("An account with this email already exists.");
        }
        // # Todo create app user object
        appUserService.save(appUser);
        return ResponseEntity.ok("User has been registered successfully.");
    }
}
