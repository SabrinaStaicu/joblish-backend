package com.codecool.travelish.service;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.repository.JobPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final JobPreferencesRepository jobPreferencesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, JobPreferencesRepository jobPreferencesRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.jobPreferencesRepository = jobPreferencesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with id: " + id));
    }

    public void save(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
    }

    public void updateJobPreferences(Long id, JobPreferences jobPreferences) {
        AppUser appUser = findById(id);
        appUser.setJobPreferences(jobPreferences);
        appUserRepository.save(appUser);
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

    public AppUser findByEmail(String email) {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with email: " + email));
    }

    public List<AppUser> getUsers() {
       return appUserRepository.findAll();
    }

    public Boolean existsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }

    public List<AppUser> searchByName(String searchInput, boolean openToWork) {
        return getUsers().stream()
                .filter(appUser -> (appUser.getFirstName() + " " + appUser.getLastName()).toLowerCase().contains(searchInput.toLowerCase()) && openToWork == appUser.getJobPreferences().isOpenToWork())
                .collect(Collectors.toList());
    }

    public void addNewSkill(Long id, String skill) {
        AppUser user = findById(id);
        user.addSkill(skill);
        appUserRepository.save(user);
    }

    public void deleteUser(@PathVariable Long id) {
        appUserRepository.deleteById(id);
    }
}



