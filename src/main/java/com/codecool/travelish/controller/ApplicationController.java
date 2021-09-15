package com.codecool.travelish.controller;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.user.AppUser;
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

    @GetMapping("/filter-by-job/{id}")
    public ResponseEntity<List<Application>> getAllByJobId(@PathVariable Long id) {
        return ResponseEntity.ok(applicationService.findAllByJobId(id));
    }

    @GetMapping("/user-has-applied/{userId}/{jobTitle}/{companyName}")
    public ResponseEntity<Boolean> userHasAlreadyApplied(@PathVariable Long userId, @PathVariable String jobTitle, @PathVariable String companyName) {
        return ResponseEntity.ok(applicationService.appUserHasApplied(userId, jobTitle, companyName));
    }

    @GetMapping("/filter-by-status/{userId}/{status}")
    public ResponseEntity<List<Application>> filterByStatus(@PathVariable Long userId, @PathVariable String status) {
        return ResponseEntity.ok(applicationService.filterByStatus(userId, status));
    }

    @GetMapping("/applications-for-company/{companyId}")
    public ResponseEntity<List<Application>> getAllForCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(applicationService.findAllCurrentApplicationForCompany(companyId));
    }

    @GetMapping("/approve-application/{id}")
    public ResponseEntity<String> approveApplication(@PathVariable Long id) {
        applicationService.approveApplication(id);
        return ResponseEntity.ok("Application with id " + id + " has been approve and other have been denied.");
    }

    @GetMapping("/reject-application/{id}")
    public ResponseEntity<String> rejectApplication(@PathVariable Long id) {
        applicationService.rejectApplication(id);
        return ResponseEntity.ok("Application with id " + id + " has been rejected.");
    }

    @GetMapping("/company-unique-applications/{companyId}")
    public ResponseEntity<List<AppUser>> getAllUniqueApplicantsForCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(applicationService.getAllCompanyApplicants(companyId));
    }
}
