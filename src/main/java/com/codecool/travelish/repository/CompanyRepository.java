package com.codecool.travelish.repository;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsByEmail(String email);

    Company findByEmail(String email);

}
