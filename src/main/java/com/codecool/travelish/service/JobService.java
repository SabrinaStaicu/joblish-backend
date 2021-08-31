package com.codecool.travelish.service;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
        return jobsRepository.findAllByTitleContaining(name);
    }
    public List<Job> findAllJobsByNameAndCategory(String category,String name) {
        return jobsRepository.findAllByCategoryAndTitleContaining(category,name);
    }

    public List<Job> findByCompanyId(Long id) {
        return jobsRepository.findAllByCompanyId(id);

    }

    public List<Job> filterJobs(String country, String jobType, ExperienceType experienceType, String category){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("salary").withIgnoreNullValues();
        Example<Job> exampleQuery = Example.of(new Job(country,jobType,experienceType,category), matcher);
        return jobsRepository.findAll(exampleQuery);

    }

}
