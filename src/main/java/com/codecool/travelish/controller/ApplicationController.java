package com.codecool.travelish.controller;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.application.ApplicationStatus;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/applications")
@PreAuthorize("hasRole('USER') or hasRole('COMPANY')")
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

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add/{userId}/{jobId}")
    public ResponseEntity<String> addApplication(@RequestBody Application application,
                                                 @PathVariable Long userId,
                                                 @PathVariable Long jobId){
        applicationService.addApplication(application, userId, jobId);
        return ResponseEntity.ok("Job added.");
    }

    @PreAuthorize("hasRole('USER')")
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

    @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/applications-for-company/{companyId}")
    public ResponseEntity<List<Application>> getAllForCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(applicationService.findAllCurrentApplicationForCompany(companyId));
    }

    @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/approve-application/{id}")
    public ResponseEntity<String> approveApplication(@PathVariable Long id) {
        applicationService.approveApplication(id);
        return ResponseEntity.ok("Application with id " + id + " has been approve and other have been denied.");
    }

    @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/reject-application/{id}")
    public ResponseEntity<String> rejectApplication(@PathVariable Long id) {
        applicationService.rejectApplication(id);
        return ResponseEntity.ok("Application with id " + id + " has been rejected.");
    }

    @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/company-unique-applications/{companyId}")
    public ResponseEntity<List<AppUser>> getAllUniqueApplicantsForCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(applicationService.getAllCompanyApplicants(companyId));
    }

    @PutMapping ("/update-status/{id}")
    public ResponseEntity<String> updateJobPreferences(@RequestBody Application application, @PathVariable Long id) {
        applicationService.updateStatus(application, id);
        return ResponseEntity.ok("Status has been updated.");
    }
}
