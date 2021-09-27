package com.codecool.travelish.controller;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.service.AppUserService;
import com.codecool.travelish.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/users")
@PreAuthorize("hasRole('USER') or hasRole('COMPANY')")
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
        appUserService.save(appUser);
        return ResponseEntity.ok("User added");
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> update(@RequestBody AppUser appUser, @PathVariable Long id) {
        appUserService.update(id, appUser);
        return ResponseEntity.ok("User has been updated.");
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping ("/update-job-preferences/{id}")
    public ResponseEntity<String> updateJobPreferences(@RequestBody JobPreferences jobPreferences, @PathVariable Long id) {
        appUserService.updateJobPreferences(id, jobPreferences);
        return ResponseEntity.ok("User has been updated.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(appUserService.getUsers());
    }

    @GetMapping("/search-user/{searchInput}/{openToWork}")
    public ResponseEntity<List<AppUser>> searchUsers(@PathVariable String searchInput, @PathVariable Boolean openToWork) {
        return ResponseEntity.ok(appUserService.searchByName(searchInput, openToWork));
    }

    @GetMapping("/add-new-skill/{userId}/{skill}")
    public ResponseEntity<String> addNewSkill(@PathVariable Long userId, @PathVariable String skill) {
        appUserService.addNewSkill(userId, skill);
        return ResponseEntity.ok("Skill added.");
    }
}
