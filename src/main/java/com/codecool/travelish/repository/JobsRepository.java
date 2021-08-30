package com.codecool.travelish.repository;

import com.codecool.travelish.model.job.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<JobOffer, Long> {
    List<JobOffer> findAllByCategory(String category);
    List<JobOffer> findAllByNameContaining(String name);
    List<JobOffer> findAllByCategoryAndNameContaining(String category, String name);

}
