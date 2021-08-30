package com.codecool.travelish.service;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobsRepository jobsRepository;

    @Autowired
    public JobService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public void saveJob(Job job) {
        jobsRepository.save(job);
    }

    public Job findById(Long id) {
        return jobsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find job with id: " + id));
    }

    public List<Job> findAllJobs() {
        System.out.println(jobsRepository.findAll());
        return jobsRepository.findAll();
    }
    public List<Job> findAllJobsByCategory(String category) {
        return jobsRepository.findAllByCategory(category);
    }
    public List<Job> findAllJobsByName(String name) {
        return jobsRepository.findAllByNameContaining(name);
    }
    public List<Job> findAllJobsByNameAndCategory(String category,String name) {
        return jobsRepository.findAllByCategoryAndNameContaining(category,name);
    }

    public List<Job> filterJobs(String category, String jobType, String country, ExperienceType experienceType){
        System.out.println(category);
        System.out.println(country);
        if (category == null && jobType == null && country == null && experienceType == null) {
            return findAllJobs();
        }
        if (category == "undefined" && jobType == "undefined" && country == "undefined") {
            return jobsRepository.findAllByExperienceType(experienceType);
        }
        if (category == "undefined" && jobType == "undefined" && experienceType == null) {
            return jobsRepository.findAllByCountry(country);
        }
        if (category == "undefined" && country == "undefined" && experienceType == null) {
            return jobsRepository.findAllByJobType(jobType);
        }
        if (jobType == "undefined" && country == "undefined" && experienceType == null) {
            return jobsRepository.findAllByCategory(category);
        }
        if (experienceType == null && country == "undefined") {
            return jobsRepository.findAllByCategoryAndJobType(category, jobType);
        }
        if (experienceType == null && category == "undefined") {
            return jobsRepository.findAllByCountryAndJobType(country, jobType);
        }
        if (experienceType == null && jobType == "undefined") {
            return jobsRepository.findAllByCategoryAndCountry(category, country);
        }
        if (category == "undefined" && jobType == "undefined") {
            return jobsRepository.findAllByCountryAndExperienceType(country, experienceType);
        }
        if (category == "undefined" && country == "undefined") {
            return jobsRepository.findAllByJobTypeAndExperienceType(jobType, experienceType);
        }
        if (jobType == "undefined" && country == "undefined") {
            return jobsRepository.findAllByExperienceTypeAndCategory(experienceType, category);
        }
        return jobsRepository.findAllByCategoryAndJobTypeAndCountryAndExperienceType(category, jobType, country, experienceType);

    }
}
