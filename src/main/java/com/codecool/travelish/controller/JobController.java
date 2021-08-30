package com.codecool.travelish.controller;

import com.codecool.travelish.model.job.JobOffer;
import com.codecool.travelish.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobOffer>> getAllJobs(){
//        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
        return ResponseEntity.ok(jobService.findAllJobs());
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobOffer>> searchJobs(@RequestParam(required=false, name="category") String category, @RequestParam(required=false, name="name") String name){
        if (name != null) {
            return ResponseEntity.ok(jobService.findAllJobsByName(name));
        }
        if (category != null) {
            return ResponseEntity.ok(jobService.findAllJobsByCategory(category));
        }
        return ResponseEntity.ok(jobService.findAllJobs());
    }
}
