package com.codecool.travelish.service;

import com.codecool.travelish.model.job.JobOffer;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class JobService {
    private JobsRepository jobsRepository;

    @Autowired
    public JobService(JobsRepository jobsRepository) {
        this.jobsRepository = jobsRepository;
    }

    public void saveJob(JobOffer jobOffer) {
        jobsRepository.save(jobOffer);
    }

    public List<JobOffer> findAllJobs() {
        return jobsRepository.findAll();
    }
}
