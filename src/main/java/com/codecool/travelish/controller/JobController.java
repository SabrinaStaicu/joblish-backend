package com.codecool.travelish.controller;

import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin("*")
@RequestMapping("/jobs")
//@PreAuthorize("hasRole('CUSTOMER') or hasRole('COMPANY')")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs(){
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(@RequestParam(required=false, name="category") String category, @RequestParam(required=false, name="name") String name){
        if (!category.equals("undefined") && name != null) {
            return ResponseEntity.ok(jobService.findAllJobsByNameAndCategory(category, name));
        }
        if (name != null) {
            return ResponseEntity.ok(jobService.findAllJobsByName(name));
        }
        if (!category.equals("undefined")) {
            return ResponseEntity.ok(jobService.findAllJobsByCategory(category));
        }
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Job>> filterJobs(@RequestParam(required=false, name="category") String category,@RequestParam(required=false, name="jobType") List<String> jobType,@RequestParam(required=false, name="country") String country,@RequestParam(required=false, name="experienceType") List<String> experienceType){
        return ResponseEntity.ok(jobService.filterJobs(country,jobType,experienceType,category));
    }

    @GetMapping("/filter-by-company/{companyId}")
    public ResponseEntity<List<Job>> filterJobsByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(jobService.findByCompanyId(companyId));
    }

    @GetMapping("/add-favorites/{userId}/{jobId}")
    public ResponseEntity<String> addToFavorites(@PathVariable Long userId, @PathVariable Long jobId) {
        jobService.addToJobToFavorites(userId, jobId);
        return ResponseEntity.ok("Job has been added to favorites.");
    }

    @GetMapping("/remove-favorites/{userId}/{jobId}")
    public ResponseEntity<String> removeFromFavorites(@PathVariable Long userId, @PathVariable Long jobId) {
        jobService.removeJobFromFavorites(userId, jobId);
        return ResponseEntity.ok("Job has been removed from favorites.");
    }

    @GetMapping("/favorites-contain-job/{userId}/{jobId}")
    public ResponseEntity<Boolean> favoritesContainJob(@PathVariable Long userId, @PathVariable Long jobId) {
        return ResponseEntity.ok(jobService.jobIsSaved(userId, jobId));
    }

    @GetMapping("/get-favorite-jobs/{userId}")
    public ResponseEntity<Set<Job>> getAllSavedJobs(@PathVariable Long userId) {
        return ResponseEntity.ok(jobService.findAllSavedJobs(userId));
    }
}
