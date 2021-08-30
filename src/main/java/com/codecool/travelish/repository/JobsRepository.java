package com.codecool.travelish.repository;

import com.codecool.travelish.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByCategory(String category);
    List<Job> findAllByNameContaining(String name);
    List<Job> findAllByCategoryAndNameContaining(String category, String name);
}
