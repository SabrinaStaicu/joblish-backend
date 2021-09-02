package com.codecool.travelish.service;

import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobsRepository jobsRepository;
    private final AppUserService appUserService;

    @Autowired
    public JobService(JobsRepository jobsRepository, AppUserService appUserService) {
        this.jobsRepository = jobsRepository;
        this.appUserService = appUserService;
    }

    public void saveJob(Job job) {
        jobsRepository.save(job);
    }

    public void saveAllJobs(List<Job> jobs) {
        jobsRepository.saveAll(jobs);
    }

    public Job findById(Long id) {
        return jobsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find job with id: " + id));
    }

    public List<Job> findAllJobs() {
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
        List<Job> filteredJobs = new ArrayList<>();
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
                filteredJobs.addAll(copy.stream().filter(job -> job.getJobType().toString().equals(jobtype)).collect(Collectors.toList()));
            }
        }

        copy2 = !filteredJobs.isEmpty() ? filteredJobs : jobs;


        long totalFilters = experienceType.stream().filter(str -> str.equals("undefined")).count();
        if (totalFilters < 4) {
            filteredJobs = new ArrayList<>();
            jobs = new ArrayList<>();
        }
        for (String experience : experienceType) {
            if (!experience.equals("undefined")) {
//                jobs = copy.stream().filter(job -> job.getJobType().equals(jobtype)).collect(Collectors.toList());
                filteredJobs.addAll(copy2.stream().filter(job -> job.getExperienceType().toString().equals(experience)).collect(Collectors.toList()));
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
        return !filteredJobs.isEmpty() ? filteredJobs : jobs;

    }

    public void addToJobToFavorites(Long userId, Long jobId) {
        AppUser user = appUserService.findById(userId);
        user.addToFavorites(findById(jobId));
        appUserService.save(user);
    }

    public void removeJobFromFavorites(Long userId, Long jobId) {
        AppUser user = appUserService.findById(userId);
        user.removeFromFavorites(findById(jobId));
        appUserService.save(user);
    }

    public Boolean jobIsSaved(Long userId, Long jobId) {
        return appUserService.findById(userId).getFavoriteJobs().contains(findById(jobId));
    }

    public Set<Job> findAllSavedJobs(Long userId) {
        return appUserService.findById(userId).getFavoriteJobs();
    }
}
