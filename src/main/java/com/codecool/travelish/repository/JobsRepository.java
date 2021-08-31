package com.codecool.travelish.repository;

import com.codecool.travelish.model.job.ExperienceType;
import com.codecool.travelish.model.job.Job;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByCategory(String category);
    List<Job> findAllByTitleContaining(String name);
    List<Job> findAllByCategoryAndTitleContaining(String category, String name);
    List<Job> findAllByCompanyId(Long id);
    List<Job> findAllByCategoryIn(List<String> lst);
}
