package com.codecool.travelish.repository;

import com.codecool.travelish.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByCategory(String category);
    List<Job> findAllByTitleContaining(String name);
    List<Job> findAllByCategoryAndTitleContaining(String category, String name);
    List<Job> findAllByCompanyId(Long id);
    Optional<Job> findById(Long id);
    List<Job> findAllByCategoryIn(List<String> lst);
}
