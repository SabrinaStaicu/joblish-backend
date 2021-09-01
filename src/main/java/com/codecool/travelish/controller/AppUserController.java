package com.codecool.travelish.controller;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/users")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appUserService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AppUser appUser){
        appUserService.addUser(appUser);
        return ResponseEntity.ok("User added");
    }
}
