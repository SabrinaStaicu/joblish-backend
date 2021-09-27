package com.codecool.travelish.service;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.application.ApplicationStatus;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final AppUserService appUserService;
    private final JobService jobService;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, AppUserService appUserService, JobService jobService) {
        this.applicationRepository = applicationRepository;
        this.appUserService = appUserService;
        this.jobService = jobService;
    }

    public Application findById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find application with id " + id));
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    public List<Application> getAllByUserId(Long id) {
        return applicationRepository.findAllByAppUserId(id);
    }

    public void save(Application application) {
        applicationRepository.save(application);
    }

    public void addApplication(Application application, Long userId, Long jobId) {
        application.setAppUser(appUserService.findById(userId));
        application.setJob(jobService.findById(jobId));
        application.setDate(LocalDate.now());
        save(application);
    }

    public void removeApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<Application> findAllByJobId(Long id) {
        return applicationRepository.findAllByJobId(id);
    }

    public Boolean appUserHasApplied(Long userId, String jobTitle, String companyName) {
        return getAllByUserId(userId).stream().anyMatch(application -> application.getJob().getTitle().equals(jobTitle) && application.getJob().getCompany().getName().equals(companyName));
    }

    public List<Application> filterByStatus(long userId, String status) {
        if (status.equals("Any_status")) {
            return getAllByUserId(userId);
        }
        return findAll().stream().filter(application -> application.getStatus().toString().equals(status) && application.getAppUser().getId() == userId).collect(Collectors.toList());
    }

    public List<Application> findAllCurrentApplicationForCompany(long id) {
        List<Application> applications = findAll().stream().filter(application -> application.getStatus() == ApplicationStatus.Not_seen || application.getStatus() == ApplicationStatus.Seen && application.getJob().getCompany().getId() == id).collect(Collectors.toList());
        return applications;
    }

    public void approveApplication(long id) {
        Application acceptedApplication = findById(id);
        acceptedApplication.setStatus(ApplicationStatus.Accepted);
        save(acceptedApplication);
        findAll().stream().filter(application -> application.getJob().getTitle().equals(acceptedApplication.getJob().getTitle()) && application.getJob().getCompany().equals(acceptedApplication.getJob().getCompany()) && application.getId() != id)
                .forEach(application -> {
                    application.setStatus(ApplicationStatus.Denied);
                    save(application);
                });
        jobService.deleteJob(acceptedApplication.getJob().getId());
    }

    public void rejectApplication(long id) {
        Application application = findById(id);
        application.setStatus(ApplicationStatus.Denied);
        save(application);
    }

    public List<AppUser> getAllCompanyApplicants(long companyId) {
        List<AppUser> applicants = new ArrayList<>();
        for(Application application: findAll()) {
            if (!applicants.contains(application.getAppUser()) && application.getJob().getCompany().getId() == companyId) {
                applicants.add(application.getAppUser());
            }
        }
        return applicants;
    }

    public void updateStatus(Application application, Long id) {
        applicationRepository.updateStatus(application.getStatus(), id);
    }

}
