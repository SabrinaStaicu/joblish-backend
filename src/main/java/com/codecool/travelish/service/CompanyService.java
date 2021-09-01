package com.codecool.travelish.service;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void save(Company company) {
        companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

}
