package com.codecool.travelish.service;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser findById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with id: " + id));
    }

    public void save(AppUser appUser) {
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
}
