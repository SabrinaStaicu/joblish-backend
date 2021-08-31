package com.codecool.travelish.controller;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/by-user-id/{id}")
    public ResponseEntity<List<Application>> getAllByUser(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.getAllByUserId(id));
    }

    @PostMapping("/add/{userId}/{jobId}")
    public ResponseEntity<String> addApplication(@RequestBody Application application,
                                                 @PathVariable Long userId,
                                                 @PathVariable Long jobId){
        applicationService.addApplication(application, userId, jobId);
        return ResponseEntity.ok("Job added.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeApplication(@PathVariable Long id) {
        applicationService.removeApplication(id);
        return ResponseEntity.ok("Application has been removed.");
    }
}
