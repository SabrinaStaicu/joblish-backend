package com.codecool.travelish.repository;

import com.codecool.travelish.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Boolean existsByEmail(String email);

    Company findByEmail(String email);

}
