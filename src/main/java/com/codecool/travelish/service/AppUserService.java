package com.codecool.travelish.service;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.repository.JobPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final JobPreferencesRepository jobPreferencesRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, JobPreferencesRepository jobPreferencesRepository) {
        this.appUserRepository = appUserRepository;
        this.jobPreferencesRepository = jobPreferencesRepository;
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with id: " + id));
    }

    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public void updateJobPreferences(Long id, JobPreferences jobPreferences) {
        AppUser appUser = findById(id);
//        appUser.setJobPreferences(jobPreferences);
        System.out.println(jobPreferences.isOpenToWork());
        jobPreferencesRepository.updateJobsPreferences(jobPreferences.isOpenToWork(), 4L);
    }

    public void update(Long id, AppUser updatedUser) {
        AppUser appUser = findById(id);
        appUser.setFirstName(updatedUser.getFirstName());
        appUser.setLastName(updatedUser.getLastName());
        appUser.setEmail(updatedUser.getEmail());
        appUser.setCity(updatedUser.getCity());
        appUser.setExperience(updatedUser.getExperience());
        appUser.setPhone(updatedUser.getPhone());
        save(appUser);
    }
}



