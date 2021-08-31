package com.codecool.travelish.service;

import com.codecool.travelish.model.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codecool.travelish.repository.ApplicationRepository;

import java.util.List;

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

    public List<Application> getAllByUserId(Long id) {
        return applicationRepository.findAllByAppUserId(id);
    }

    public void save(Application application) {
        applicationRepository.save(application);
    }

    public void addApplication(Application application, Long userId, Long jobId) {
        application.setAppUser(appUserService.findById(userId));
        application.setJob(jobService.findById(jobId));
        save(application);
    }

    public void removeApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<Application> findAllByJobId(Long id) {
        return applicationRepository.findAllByJobId(id);
    }
}
