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

    public void addUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }
}
