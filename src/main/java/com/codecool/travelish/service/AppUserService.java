package com.codecool.travelish.service;

import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.model.user.JobPreferences;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.repository.JobPreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final JobPreferencesRepository jobPreferencesRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, JobPreferencesRepository jobPreferencesRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.jobPreferencesRepository = jobPreferencesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email);
        if(appUser == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(appUser.getRole().name()));
        System.out.println("YES");
        return new User(appUser.getEmail(), appUser.getPassword(),authorities);
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

    public List<AppUser> getUsers() {
       return appUserRepository.findAll();
    }


}



