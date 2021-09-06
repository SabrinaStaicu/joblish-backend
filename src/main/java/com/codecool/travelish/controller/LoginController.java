package com.codecool.travelish.controller;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.security.fillter.CustomAuthenticationFilter;
import com.codecool.travelish.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AppUserService appUserService;

    @Autowired
    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping()
    public ResponseEntity<String> logUser(@RequestBody AppUser appUser){
        System.out.println(appUser.getEmail());
        appUserService.loadUserByUsername(appUser.getEmail());
        return ResponseEntity.ok("Logged in");
    }
}
