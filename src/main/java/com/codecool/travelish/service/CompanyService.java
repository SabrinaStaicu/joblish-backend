package com.codecool.travelish.service;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Company company) {
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        companyRepository.save(company);
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

    public Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find company with id " + id));
    }
}
