package com.codecool.travelish.controller;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        appUserService.save(appUser);
        return ResponseEntity.ok("User added");
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<String> update(@RequestBody AppUser appUser, @PathVariable Long id) {
        appUserService.update(id, appUser);
        return ResponseEntity.ok("User has been updated.");
    }

    @PutMapping ("/update-job-preferences/{id}")
    public ResponseEntity<String> updateJobPreferences(@RequestBody JobPreferences jobPreferences, @PathVariable Long id) {
        appUserService.updateJobPreferences(id, jobPreferences);
        return ResponseEntity.ok("User has been updated.");
    }


}
