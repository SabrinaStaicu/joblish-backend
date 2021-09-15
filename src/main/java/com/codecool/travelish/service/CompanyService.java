package com.codecool.travelish.service;

import com.codecool.travelish.model.application.Application;
import com.codecool.travelish.model.application.ApplicationStatus;
import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.user.AppUser;
import com.codecool.travelish.repository.AppUserRepository;
import com.codecool.travelish.repository.ApplicationRepository;
import com.codecool.travelish.repository.CompanyRepository;
import com.codecool.travelish.repository.JobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final AppUserRepository appUserRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    public void save(Company company) {
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        companyRepository.save(company);
    }

//    public List<Application> findAllApplications() {
//        return applicationRepository.findAll();
//    }
//
//    public List<Job> findAllAppliedJobs(Long id) {
//        List<Job> jobs = new ArrayList<>();
//        findAllApplications().stream().forEach(application -> {
//            jobs.add(jobsRepository.findById(application.getJob().getId()).orElseThrow(() -> new IllegalArgumentException("Could not find jobs! ")));
//        });
//
//        return jobs.stream().filter(job -> job.getCompany().getId().equals(id)).collect(Collectors.toList());
//    }

    public List<AppUser> findAllUsersByCompany(long id) {
        Pageable pageable =  PageRequest.of(0,5);
        return  appUserRepository.selectUsersByCompany(id, pageable);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }

    public Company findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public void changePassword(Long companyId, String password) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new IllegalArgumentException("Could not find company."));
        company.setPassword(passwordEncoder.encode(password));
        companyRepository.save(company);
    }
}
