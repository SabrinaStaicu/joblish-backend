package com.codecool.travelish.repository;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByCategory(String category);
    List<Job> findAllByNameContaining(String name);
    List<Job> findAllByCategoryAndNameContaining(String category, String name);
    List<Job> findAllByCategoryAndJobTypeAndCountryAndExperienceType(String category, String jobType, String country, ExperienceType experienceType);
    List<Job> findAllByCategoryAndJobTypeAndCountry(String category, String jobType, String country);
    List<Job> findAllByCategoryAndJobType(String category, String jobType);
    List<Job> findAllByCountryAndExperienceType(String country, ExperienceType experienceType);
    List<Job> findAllByCountryAndJobType(String country, String jobType);
    List<Job> findAllByCategoryAndCountry(String category, String country);
    List<Job> findAllByJobTypeAndExperienceType(String jobType, ExperienceType experienceType);
    List<Job> findAllByExperienceTypeAndCategory(ExperienceType experienceType, String category);
    List<Job> findAllByJobType(String jobType);
    List<Job> findAllByCountry(String country);
    List<Job> findAllByExperienceType(ExperienceType experienceType);

}
