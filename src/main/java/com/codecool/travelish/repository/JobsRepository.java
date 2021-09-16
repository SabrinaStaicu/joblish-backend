package com.codecool.travelish.repository;

import com.codecool.travelish.model.company.Company;
import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import com.codecool.travelish.model.job.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByCategory(String category);
    List<Job> findAllByTitleContaining(String name);
    List<Job> findAllByCategoryAndTitleContaining(String category, String name);
    List<Job> findAllByCompanyId(Long id);
    Optional<Job> findById(Long id);

    @Modifying
    @Transactional
    @Query("update Job j set j.category = ?1, j.city = ?2, j.country = ?3, j.date = ?4, j.description = ?5, j.jobType = ?6, j.experienceType = ?7, j.salary = ?8, j.title = ?9  where j.id = ?10")
    void updateJobDetails(String category, String city, String country, LocalDate date, String description, JobType jobType, ExperienceType experienceType, int salary, String title, Long id);

    List<Job> findAllByCategoryIn(List<String> lst);
}
