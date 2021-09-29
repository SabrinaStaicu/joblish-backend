package com.codecool.travelish.service;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.CompanyRepository;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobsRepository jobsRepository;
    private final AppUserService appUserService;
    private final CompanyRepository companyRepository;

    @Autowired
    public JobService(JobsRepository jobsRepository, AppUserService appUserService, CompanyRepository companyRepository) {
        this.jobsRepository = jobsRepository;
        this.appUserService = appUserService;
        this.companyRepository = companyRepository;
    }

    public Job saveJob(Job job, Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("You need to Log In"));
        job.setCompany(company);
        return jobsRepository.save(job);
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

    public List<Job> filterByCountryAndCategory(String country, String category) {
        List<Job> jobs = jobsRepository.findAll();
        if (!category.equals("undefined")) {
            jobs = jobs.stream().filter(job -> job.getCategory().equals(category)).collect(Collectors.toList());
        }
        if (!country.equals("undefined")) {
            jobs = jobs.stream().filter(job -> job.getCountry().equals(country)).collect(Collectors.toList());
        }
        return jobs;
    }

    public List<Job> filterJobs(String country, List<String> jobType, List<String> experienceType, String category){
        List<Job> jobs = filterByCountryAndCategory(country, category);
        List<Job> filteredJobs = new ArrayList<>();
        List<Job> firstCopy;
        List<Job> secondCopy;
        firstCopy = jobs;
        for (String jobtype : jobType) {
            if (!jobtype.equals("undefined")) {
                filteredJobs.addAll(firstCopy.stream().filter(job -> job.getJobType().toString().equals(jobtype)).collect(Collectors.toList()));
            }
        }
        secondCopy = !filteredJobs.isEmpty() ? filteredJobs : jobs;
        long totalFilters = experienceType.stream().filter(str -> str.equals("undefined")).count();
        if (totalFilters < 4) {
            filteredJobs = new ArrayList<>();
            jobs = new ArrayList<>();
        }
        for (String experience : experienceType) {
            if (!experience.equals("undefined")) {
                filteredJobs.addAll(secondCopy.stream().filter(job -> job.getExperienceType().toString().equals(experience)).collect(Collectors.toList()));
            }
        }
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

    public void updateJobDetails(Job job,  Long id) {
        jobsRepository.updateJobDetails(job.getCategory(),job.getCity(),job.getCountry(), job.getDate(), job.getDescription(), job.getJobType(), job.getExperienceType(), job.getSalary(), job.getTitle(), id);
    }

    public void deleteJob(@PathVariable Long id) {
        jobsRepository.deleteById(id);
    }
}
