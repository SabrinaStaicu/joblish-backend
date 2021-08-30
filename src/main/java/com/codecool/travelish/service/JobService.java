package com.codecool.travelish.service;

import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private JobsRepository jobsRepository;

    @Autowired
    public JobService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public void saveJob(Job job) {
        jobsRepository.save(job);
    }

    public List<Job> findAllJobs() {
        return jobsRepository.findAll();
    }
}
