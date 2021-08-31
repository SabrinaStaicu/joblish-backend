package com.codecool.travelish.service;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Job> filterJobs(String country, List<String> jobType, List<String> experienceType, String category){
        List<Job> jobs = jobsRepository.findAll();
        List<Job> fillteredJobs = new ArrayList<>();
        List<Job> copy;
        List<Job> copy2 = new ArrayList<>();

        if (!category.equals("undefined")) {
            jobs = jobs.stream().filter(job -> job.getCategory().equals(category)).collect(Collectors.toList());

        }


        if (!country.equals("undefined")) {
            jobs = jobs.stream().filter(job -> job.getCountry().equals(country)).collect(Collectors.toList());
        }

        copy= jobs;

        for (String jobtype : jobType) {
            if (!jobtype.equals("undefined")) {
//                jobs = copy.stream().filter(job -> job.getJobType().equals(jobtype)).collect(Collectors.toList());
                fillteredJobs.addAll(copy.stream().filter(job -> job.getJobType().equals(jobtype)).collect(Collectors.toList()));
            }
        }

        copy2 = !fillteredJobs.isEmpty() ? fillteredJobs : jobs;


        long totalFilters = experienceType.stream().filter(str -> str.equals("undefined")).count();
        if (totalFilters < 4) {
            fillteredJobs = new ArrayList<>();
            jobs = new ArrayList<>();
        }


        for (String experience : experienceType) {
            if (!experience.equals("undefined")) {
//                jobs = copy.stream().filter(job -> job.getJobType().equals(jobtype)).collect(Collectors.toList());
                fillteredJobs.addAll(copy2.stream().filter(job -> job.getExperienceType().toString().equals(experience)).collect(Collectors.toList()));
            }
        }





//        if (category.equals("undefined")) {
//            category = null;
//        }
//        if (jobType.equals("undefined")) {
//            jobType = null;
//        }
//        if (country.equals("undefined")) {
//            country = null;
//        }
//
//        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("salary").withIgnoreNullValues();
//        Example<Job> exampleQuery = Example.of(new Job(country,jobType,experienceType,category), matcher);
        return !fillteredJobs.isEmpty() ? fillteredJobs : jobs;

    }
}
